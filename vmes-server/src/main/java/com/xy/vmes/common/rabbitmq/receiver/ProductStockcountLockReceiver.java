package com.xy.vmes.common.util.rabbitmq.receiver;

import com.xy.vmes.deecoop.base.service.ProductService;
import com.yvan.PageData;
import com.yvan.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消费者 消息接收
 * @author 陈刚
 * @date 2019-06-13
 */
@Component
public class ProductStockcountLockReceiver {
//    @Autowired
//    private SaleOrderDetailService saleOrderDetailService;
    @Autowired
    private ProductService productService;

    //@RabbitListener(queues = RabbitMqConfig.QUEUE_TEST)
    @RabbitListener(queues = RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_QUEUE)
    public void receive(String rabbitMq_msg) throws Exception{
        //System.out.println("(Product)接收队列消息： " + rabbitMq_msg);

        if (rabbitMq_msg == null || rabbitMq_msg.trim().length() == 0) {
            System.out.println("rabbitMq消息队列-收到报文(rabbitMq_msg) 为空或空字符串");
        } else {
            System.out.println("rabbitMq消息队列-收到报文(rabbitMq_msg):" + rabbitMq_msg);
        }

        if (rabbitMq_msg == null || rabbitMq_msg.trim().length() == 0) {return;}
        String[] strArray = rabbitMq_msg.split(",");

        String orderDtlId = "";
        if (strArray != null && strArray.length >= 1) {
            orderDtlId = strArray[0];
        }

        String versionLockCount = "";
        if (strArray != null && strArray.length >= 2) {
            versionLockCount = strArray[1];
        }

        PageData findMap = new PageData();
        findMap.put("id", orderDtlId);
        findMap.put("versionLockCount", versionLockCount);

//        SaleOrderDetail orderDetail = saleOrderDetailService.findSaleOrderDetail(findMap);
//        //is_lock_warehouse 是否锁定仓库(0:未锁定 1:已锁定
//        if (orderDetail != null && "1".equals(orderDetail.getIsLockWarehouse())) {
//            //锁定货品数量
//            BigDecimal lockCount = BigDecimal.valueOf(0D);
//            if (orderDetail.getLockCount() != null) {
//                lockCount = orderDetail.getLockCount();
//            }
//
//            //货品ID
//            String productId = orderDetail.getProductId();
//            if (productId != null && productId.trim().length() > 0) {
//                lockCount = BigDecimal.valueOf(lockCount.doubleValue() * -1);
//                productService.updateLockCount(productId, null, lockCount, null);
//            }
//
//            //isLockWarehouse 是否锁定仓库(0:未锁定 1:已锁定
//            SaleOrderDetail orderDtlEdit = new SaleOrderDetail();
//            orderDtlEdit.setId(orderDetail.getId());
//            //是否锁定仓库(0:未锁定 1:已锁定)
//            orderDtlEdit.setIsLockWarehouse("0");
//            orderDtlEdit.setLockCount(BigDecimal.valueOf(0D));
//            saleOrderDetailService.update(orderDtlEdit);
//        }
    }

}
