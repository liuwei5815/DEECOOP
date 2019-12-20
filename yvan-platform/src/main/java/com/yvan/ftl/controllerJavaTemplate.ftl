package ${classPath};

import com.xy.vmes.service.${objectName}Service;
import com.xy.vmes.entity.${objectName};

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
* 说明：${TITLE}Controller
* @author ${author} 自动生成
* @date ${nowDate?string("yyyy-MM-dd")}
*/
@RestController
@Slf4j
public class ${objectName}Controller {

    private Logger logger = LoggerFactory.getLogger(${objectName}Controller.class);

    @Autowired
    private ${objectName}Service ${objectNameLower}Service;

    /**
    * @author ${author} 自动创建，禁止修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @GetMapping("/${moduleName}/${objectNameLower}/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/${moduleName}/${objectNameLower}/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        ${objectName} ${objectNameLower} = ${objectNameLower}Service.selectById(id);
        model.putResult(${objectNameLower});
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author ${author} 自动创建，禁止修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @PostMapping("/${moduleName}/${objectNameLower}/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/${moduleName}/${objectNameLower}/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ${objectName} ${objectNameLower} = (${objectName})HttpUtils.pageData2Entity(pd, new ${objectName}());
        ${objectNameLower}Service.save(${objectNameLower});
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author ${author} 自动创建，禁止修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @PostMapping("/${moduleName}/${objectNameLower}/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/${moduleName}/${objectNameLower}/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        ${objectName} ${objectNameLower} = (${objectName})HttpUtils.pageData2Entity(pd, new ${objectName}());
        ${objectNameLower}Service.update(${objectNameLower});
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author ${author} 自动创建，禁止修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @GetMapping("/${moduleName}/${objectNameLower}/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/${moduleName}/${objectNameLower}/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        ${objectNameLower}Service.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author ${author} 自动创建，可以修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @PostMapping("/${moduleName}/${objectNameLower}/listPage${objectName}s")
    public ResultModel listPage${objectName}s()  throws Exception {
        logger.info("################/${moduleName}/${objectNameLower}/listPage${objectName}s 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = ${objectNameLower}Service.listPage${objectName}s(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/listPage${objectName}s 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author ${author} 自动创建，可以修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @PostMapping("/${moduleName}/${objectNameLower}/exportExcel${objectName}s")
    public void exportExcel${objectName}s() throws Exception {
        logger.info("################/${moduleName}/${objectNameLower}/exportExcel${objectName}s 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ${objectNameLower}Service.exportExcel${objectName}s(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/exportExcel${objectName}s 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author ${author} 自动创建，可以修改
    * @date ${nowDate?string("yyyy-MM-dd")}
    */
    @PostMapping("/${moduleName}/${objectNameLower}/importExcel${objectName}s")
    public ResultModel importExcel${objectName}s(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/${moduleName}/${objectNameLower}/importExcel${objectName}s 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = ${objectNameLower}Service.importExcel${objectName}s(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/${moduleName}/${objectNameLower}/importExcel${objectName}s 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



