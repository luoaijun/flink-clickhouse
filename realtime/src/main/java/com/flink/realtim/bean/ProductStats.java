package com.flink.realtim.bean;

/**
 * Author: Felix
 * Date: 2021/2/23
 * @Builder注解
 *      可以使用构造者方式创建对象，给属性赋值
 * @Builder.Default
 *      在使用构造者方式给属性赋值的时候，属性的初始值会丢失
 *      该注解的作用就是修复这个问题
 *      例如：我们在属性上赋值了初始值为0L，如果不加这个注解，通过构造者创建的对象属性值会变为null
 */

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class ProductStats {

    String stt;//窗口起始时间
    String edt;  //窗口结束时间
    Long sku_id; //sku编号
    String sku_name;//sku名称
    BigDecimal sku_price; //sku单价
    Long spu_id; //spu编号
    String spu_name;//spu名称
    Long tm_id; //品牌编号
    String tm_name;//品牌名称
    Long category3_id;//品类编号
    String category3_name;//品类名称

    @Builder.Default
    Long display_ct = 0L; //曝光数

    @Builder.Default
    Long click_ct = 0L;  //点击数

    @Builder.Default
    Long favor_ct = 0L; //收藏数

    @Builder.Default
    Long cart_ct = 0L;  //添加购物车数

    @Builder.Default
    Long order_sku_num = 0L; //下单商品个数

    @Builder.Default   //下单商品金额
    BigDecimal order_amount = BigDecimal.ZERO;

    @Builder.Default
    Long order_ct = 0L; //订单数

    @Builder.Default   //支付金额
    BigDecimal payment_amount = BigDecimal.ZERO;

    @Builder.Default
    Long paid_order_ct = 0L;  //支付订单数

    @Builder.Default
    Long refund_order_ct = 0L; //退款订单数

    @Builder.Default
    BigDecimal refund_amount = BigDecimal.ZERO;

    @Builder.Default
    Long comment_ct = 0L;//评论数

    @Builder.Default
    Long good_comment_ct = 0L; //好评数

    @Builder.Default
    @TransientSink
    Set orderIdSet = new HashSet();  //用于统计订单数

    @Builder.Default
    @TransientSink
    Set paidOrderIdSet = new HashSet(); //用于统计支付订单数

    @Builder.Default
    @TransientSink
    Set refundOrderIdSet = new HashSet();//用于退款支付订单数

    Long ts; //统计时间戳

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getEdt() {
        return edt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public BigDecimal getSku_price() {
        return sku_price;
    }

    public void setSku_price(BigDecimal sku_price) {
        this.sku_price = sku_price;
    }

    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public Long getTm_id() {
        return tm_id;
    }

    public void setTm_id(Long tm_id) {
        this.tm_id = tm_id;
    }

    public String getTm_name() {
        return tm_name;
    }

    public void setTm_name(String tm_name) {
        this.tm_name = tm_name;
    }

    public Long getCategory3_id() {
        return category3_id;
    }

    public void setCategory3_id(Long category3_id) {
        this.category3_id = category3_id;
    }

    public String getCategory3_name() {
        return category3_name;
    }

    public void setCategory3_name(String category3_name) {
        this.category3_name = category3_name;
    }

    public Long getDisplay_ct() {
        return display_ct;
    }

    public void setDisplay_ct(Long display_ct) {
        this.display_ct = display_ct;
    }

    public Long getClick_ct() {
        return click_ct;
    }

    public void setClick_ct(Long click_ct) {
        this.click_ct = click_ct;
    }

    public Long getFavor_ct() {
        return favor_ct;
    }

    public void setFavor_ct(Long favor_ct) {
        this.favor_ct = favor_ct;
    }

    public Long getCart_ct() {
        return cart_ct;
    }

    public void setCart_ct(Long cart_ct) {
        this.cart_ct = cart_ct;
    }

    public Long getOrder_sku_num() {
        return order_sku_num;
    }

    public void setOrder_sku_num(Long order_sku_num) {
        this.order_sku_num = order_sku_num;
    }

    public BigDecimal getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(BigDecimal order_amount) {
        this.order_amount = order_amount;
    }

    public Long getOrder_ct() {
        return order_ct;
    }

    public void setOrder_ct(Long order_ct) {
        this.order_ct = order_ct;
    }

    public BigDecimal getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(BigDecimal payment_amount) {
        this.payment_amount = payment_amount;
    }

    public Long getPaid_order_ct() {
        return paid_order_ct;
    }

    public void setPaid_order_ct(Long paid_order_ct) {
        this.paid_order_ct = paid_order_ct;
    }

    public Long getRefund_order_ct() {
        return refund_order_ct;
    }

    public void setRefund_order_ct(Long refund_order_ct) {
        this.refund_order_ct = refund_order_ct;
    }

    public BigDecimal getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(BigDecimal refund_amount) {
        this.refund_amount = refund_amount;
    }

    public Long getComment_ct() {
        return comment_ct;
    }

    public void setComment_ct(Long comment_ct) {
        this.comment_ct = comment_ct;
    }

    public Long getGood_comment_ct() {
        return good_comment_ct;
    }

    public void setGood_comment_ct(Long good_comment_ct) {
        this.good_comment_ct = good_comment_ct;
    }

    public Set getOrderIdSet() {
        return orderIdSet;
    }

    public void setOrderIdSet(Set orderIdSet) {
        this.orderIdSet = orderIdSet;
    }

    public Set getPaidOrderIdSet() {
        return paidOrderIdSet;
    }

    public void setPaidOrderIdSet(Set paidOrderIdSet) {
        this.paidOrderIdSet = paidOrderIdSet;
    }

    public Set getRefundOrderIdSet() {
        return refundOrderIdSet;
    }

    public void setRefundOrderIdSet(Set refundOrderIdSet) {
        this.refundOrderIdSet = refundOrderIdSet;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
