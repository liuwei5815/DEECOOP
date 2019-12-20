package com.yvan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.yvan.platform.JsonWapper;
import com.yvan.platform.JsonWapperSerializer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.*;

public class YvanUtil {

    public static final ObjectMapper objectMapper;
    public static final ObjectMapper objectYamlMapper;

    private static ResourceLoader loader = new DefaultResourceLoader();
    private static Random random = new Random();

    static {

        SimpleModule longSimpleModule = new SimpleModule();
        longSimpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        longSimpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.registerModule(JsonWapperSerializer.MODEL);
        objectMapper.registerModule(longSimpleModule);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        objectMapper.writerWithDefaultPrettyPrinter();

        objectYamlMapper = new ObjectMapper(new YAMLFactory());
        objectYamlMapper.registerModule(new JodaModule());
        objectYamlMapper.registerModule(JsonWapperSerializer.MODEL);
        objectYamlMapper.registerModule(longSimpleModule);
        objectYamlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectYamlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectYamlMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        objectYamlMapper.writerWithDefaultPrettyPrinter();
    }

    private static final char[] DIG_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char getShortDig(int num) {
        return DIG_ARRAY[num];
    }

    public static String mysqlLike(String likeContent) {
        if (StringUtils.isBlank(likeContent)) {
            return "";
        }
        return "'%" + likeContent.trim()
                .replace("'", "\\'")
                .replace("/", "//")
                .replace("_", "/_")
                .replace("%", "/%") +
                "%' escape '/'";
    }

    /**
     * crm.yyjzt.com[:80] -> yyjzt.com
     * aaaa.test.yyjzt.com[:80] -> yyjzt.com
     * 192.168.1.1[:80] -> 192.168.1.1
     * localhost[:80] -> localhost
     *
     * @return yyjzt.com / 192.168.1.1 / localhost
     */
    public static String rootDomain(String hostName) {
        hostName = hostName.toLowerCase();
        int portPos = hostName.indexOf(':');
        if (portPos > 0) {
            hostName = hostName.substring(0, portPos);
        }

        if (isDomain(hostName)) {
            int pos = hostName.lastIndexOf('.');
            if (pos < 0) {
                return hostName;
            }

            int pos2 = hostName.lastIndexOf('.', pos - 1);
            if (pos2 < 0) {
                return hostName;
            }

            return hostName.substring(pos2 + 1);
        }
        return hostName;
    }

    /**
     * crm.yyjzt.com[:80] -> true
     * aaaa.test.yyjzt.com[:80] -> true
     * 192.168.1.1[:80] -> false
     * localhost[:80] -> false
     *
     * @return 判断hostName是不是域名
     */
    public static boolean isDomain(String host) {
        final String hostName = host.toLowerCase();
        return (hostName.endsWith(".com") ||
                hostName.endsWith(".net") ||
                hostName.endsWith(".org") ||
                hostName.endsWith(".cn") ||
                hostName.endsWith(".gov") ||
                hostName.endsWith(".edu") ||
                hostName.endsWith(".me"));
    }

    public static JsonWapper readYaml(File file) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectYamlMapper.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (jsonNode == null) {
            return new JsonWapper();
        }

        try {
            return new JsonWapper(objectMapper.writeValueAsString(jsonNode));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonWapper readYaml(String content) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectYamlMapper.readTree(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (jsonNode == null) {
            return new JsonWapper();
        }

        try {
            return new JsonWapper(objectMapper.writeValueAsString(jsonNode));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonWapper readYamlWapper(Collection<String> locations) {
        JsonNode jsonNode = null;
        for (String location : locations) {
            Resource resource = loader.getResource(location);

            InputStream is = null;
            try {
                is = resource.getInputStream();
                if (jsonNode == null) {
                    jsonNode = objectYamlMapper.readTree(is);
                } else {
                    merge(jsonNode, objectYamlMapper.readTree(is));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }

        if (jsonNode == null) {
            return new JsonWapper();
        }

        try {
            return new JsonWapper(objectMapper.writeValueAsString(jsonNode));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {

        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {

            String fieldName = fieldNames.next();
            JsonNode mainJsonNode = mainNode.get(fieldName);
            JsonNode updateJsonNode = updateNode.get(fieldName);

            // if field exists and is an embedded object
            if (mainJsonNode != null && mainJsonNode.isObject()) {
                merge(mainJsonNode, updateNode.get(fieldName));
            } else {
                if (mainJsonNode != null && mainJsonNode.isArray()) {
                    if (updateJsonNode != null) {
                        ArrayNode arrayNode = (ArrayNode) mainJsonNode;
                        if (updateJsonNode.isArray()) {
                            //合并2个数组
                            arrayNode.addAll((ArrayNode) updateJsonNode);
                        } else {
                            arrayNode.add(updateJsonNode);
                        }
                    }
                } else if (mainNode instanceof ObjectNode) {
                    // Overwrite field
                    JsonNode value = updateNode.get(fieldName);
                    ((ObjectNode) mainNode).set(fieldName, value);
                }
            }

        }

        return mainNode;
    }

    public static JsonWapper merge(String mainNode, String updateNode) {
        try {
            return new JsonWapper(objectMapper.writeValueAsString(merge(objectMapper.readTree(mainNode), objectMapper.readTree(updateNode))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonWapper readYamlWapper(String location) {
        Resource resource = loader.getResource(location);
        InputStream is = null;
        try {
            is = resource.getInputStream();
            return new JsonWapper(objectYamlMapper.readValue(is, LinkedHashMap.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static JsonWapper readJsonWapper(String location) {
        return readJsonWapper(loader.getResource(location));
    }

    public static JsonWapper readJsonWapper(Resource resource) {
        if (resource != null && resource.exists()) {
            if (resource.isReadable()) {
                InputStream is = null;
                try {
                    is = resource.getInputStream();
                    return new JsonWapper(is);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    IOUtils.closeQuietly(is);
                }
            }
        }
        throw new RuntimeException("resource can't found!");
    }

    public static String joinCollection(Iterable<?> iterable, String spt, String emptyString) {
        StringBuilder sb = new StringBuilder();
        String sp = "";
        Iterator<?> iter = iterable.iterator();

        while (iter.hasNext()) {
            Object o = iter.next();
            sb.append(sp).append(Conv.NS(o));
            sp = spt;
        }
        if (sb.length() <= 0) {
            return emptyString;
        } else {
            return sb.toString();
        }
    }

    public static String joinCollection(Iterable<?> iterable, String spt) {
        return joinCollection(iterable, spt, "");
    }

    public static <T> T mapToEntities(Object listMap, Class<T> clazz) {
        try {
            String jsonString = objectMapper.writeValueAsString(listMap);
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Json字符串序列化成指定对象
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        try {
            if (clazz.equals(String.class)) {
                return (T) jsonStr;
            }
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存内容到文件
     *
     * @param filePath 文件路径
     * @param content  保存的内容
     */
    public static void saveToFile(String filePath, String content) {
        BufferedWriter bufferedWriter = null;
        OutputStreamWriter outputStreamWriter = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    /**
     * 将Json字符串序列化成 list
     */
    public static List<?> jsonToList(String jsonInput) {
        try {
            return objectMapper.readValue(jsonInput, List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Json字符串序列化成json
     */
    public static Map<?, ?> jsonToMap(String jsonInput) {
        try {
            return objectMapper.readValue(jsonInput, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象序列化成json
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象序列化成 yaml
     */
    public static String toYaml(Object obj) {
        try {
            return objectYamlMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象序列化成json
     */
    public static String toJsonPretty(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件中读取所有内容
     */
    public static String readFile(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return StringUtils.join(IOUtils.readLines(fis, "UTF-8"), "\r\n");
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX)
                .substring(1);
    }

    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(createUUID());
        String j = "{\"1\":80.0,\"2\":16.0,\"3\":4.0}";
        System.out.println(jsonToMap(j));
    }

    /**
     * 产生随机数，从0到intVal(包含intVal)
     */
    public static int getRandomNotContain(int intVal) {
        return random.nextInt(intVal);
    }

    /**
     * 产生随机数，从0到intVal(包含intVal)
     */
    public static int getRandom(int intVal) {
        return random.nextInt(intVal + 1);
    }

    /**
     * 产生随机数，从max到min，包含max和min
     */
    public static int getRandom(int val1, int val2) {
        if (Integer.compare(val1, val2) == 0) {
            return val1;
        }
        int min, max;
        if (val1 > val2) {
            max = val1;
            min = val2;
        } else {
            max = val2;
            min = val1;
        }
        return min + random.nextInt(max - min + 1);
    }

    public static byte[] toJsonByte(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
