//package com.xy.vmes.demo.controller;
//
//
//import com.yvan.HttpUtils;
//import com.yvan.PageData;
//import com.yvan.springmvc.ResultModel;
//import org.flowable.bpmn.model.BpmnModel;
//import org.flowable.engine.*;
//import org.flowable.engine.runtime.Execution;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.flowable.image.ProcessDiagramGenerator;
//import org.flowable.task.api.Task;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * 报销demoController
// *
// * @author puhaiyang
// * @date 2018/12/19
// */
//@RestController
//public class FlowableController {
//    @Autowired
//    private RuntimeService runtimeService;
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private RepositoryService repositoryService;
//    @Autowired
//    private ProcessEngine processEngine;
//
///***************此处为业务代码******************/
//    /**
//     * 添加报销
//     *
//     */
//    @GetMapping("/test/addExpense")
//    public ResultModel addExpense() {
//        ResultModel model = new ResultModel();
//        PageData pd = HttpUtils.parsePageData();
//        String userId = pd.getString("userId");
//        String money = pd.getString("money");
//        //启动流程
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("taskUser", userId);
//        map.put("money", money);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Expense", map);
//        model.putMsg("提交成功.流程Id为：" + processInstance.getId());
//        return model;
//    }
//
//    /**
//     * 获取审批管理列表
//     */
//    @GetMapping("/test/list")
//    public ResultModel list() {
//        ResultModel model = new ResultModel();
//        PageData pd = HttpUtils.parsePageData();
//        String userId = pd.getString("userId");
//        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
//        String msg = null;
//        for (Task task : tasks) {
//            msg = msg + task.toString()+"/n";
//            System.out.println(task.toString());
//        }
////        model.put("tasks",tasks);
//        model.putMsg(msg);
//        return model;
//    }
//
//    /**
//     * 批准
//     *
//     */
//    @GetMapping("/test/apply")
//    public ResultModel apply() {
//        ResultModel model = new ResultModel();
//        PageData pd = HttpUtils.parsePageData();
//        String taskId = pd.getString("taskId");
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if (task == null) {
//            throw new RuntimeException("流程不存在");
//        }
//        //通过审核
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "通过");
//        taskService.complete(taskId, map);
//        model.putMsg("processed ok!");
//        return model;
//    }
//
//    /**
//     * 拒绝
//     */
//    @GetMapping("/test/reject")
//    public ResultModel reject() {
//        ResultModel model = new ResultModel();
//        PageData pd = HttpUtils.parsePageData();
//        String taskId = pd.getString("taskId");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "驳回");
//        taskService.complete(taskId, map);
//        model.putMsg("reject");
//        return model;
//    }
//
//    /**
//     * 生成流程图
//     *
//     */
//    @GetMapping("/test/genProcessDiagram")
//    public void genProcessDiagram() throws Exception {
//        HttpServletResponse httpServletResponse = HttpUtils.currentResponse();
//
//        httpServletResponse.reset();
//        httpServletResponse.setHeader("Content-Type","application/octet-stream" );
//        httpServletResponse.setHeader("Connection", "close");
//        httpServletResponse.setHeader("Content-Disposition","attachment;filename=process.png" );
//
//        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.addHeader("Access-Control-Allow-Methods", "*");
//        httpServletResponse.addHeader("Access-Control-Max-Age", "100");
//        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
//        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "false");
//
//
//        PageData pd = HttpUtils.parsePageData();
//        String processId = pd.getString("processId");
//
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//
//        //流程走完的不显示图
//        if (pi == null) {
//            return;
//        }
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        String InstanceId = task.getProcessInstanceId();
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(InstanceId)
//                .list();
//
//        //得到正在执行的Activity的Id
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        //获取流程图
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = httpServletResponse.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//}