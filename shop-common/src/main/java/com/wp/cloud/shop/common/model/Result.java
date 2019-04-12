package com.wp.cloud.shop.common.model;


import com.wp.cloud.shop.common.enumerate.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Title: com.wp.cloud.shop.common.model.Result
 * Description: http响应正常处理结果
 * Copyright: Copyright (c) 2017/12
 * Create DateTime: 2019/04/11 上午2:14
 *
 * @author suanmilk
 * @version v2
 */
@ApiModel
public class Result<T> implements Serializable {
    /**
     * 状态码
     **/
    @ApiModelProperty(value = "状态码")
    private Integer code;
    /**
     * 微服务名称
     **/
    @ApiModelProperty(value = "微服务名称")
    private String serviceName;
    /**
     * 返回数据集
     **/
    @ApiModelProperty(value = "返回数据集")
    private T results;
    /**
     * 返回信息
     **/
    @ApiModelProperty(value = "返回信息")
    private String msg;
    /**
     * 记录总数
     **/
    @ApiModelProperty(value = "记录总数")
    private Long total;

    public Result() {
    }

    private Result(JsonResultBuilder<T> builder) {
        this.results = builder.results;
        this.msg = builder.msg;
        this.code = builder.code;
        this.serviceName = builder.serviceName;
        this.total = builder.total;
    }

    public static <T> Result.JsonResultBuilder<T> builder() {
        return new JsonResultBuilder<>();
    }

    public static final class JsonResultBuilder<T> {

        private T results;

        private String msg;

        private Integer code;

        private String serviceName;

        private Long total;

        private JsonResultBuilder() {
        }

        public JsonResultBuilder ok() {
            return ok("操作成功");
        }

        public JsonResultBuilder ok(String msg) {
            this.msg = msg;
            this.code = HttpStatus.OK.value();
            return this;
        }

        public JsonResultBuilder create(String msg) {
            this.msg = msg;
            this.code = HttpStatus.CREATED.value();
            return this;
        }

        public JsonResultBuilder error(String msg) {
            this.msg = msg;
            this.code = HttpStatus.BAD_REQUEST.value();
            return this;
        }

        public JsonResultBuilder conflict(String msg) {
            this.msg = msg;
            this.code = HttpStatus.CONFLICT.value();
            return this;
        }

        public JsonResultBuilder notFound(String msg) {
            this.msg = msg;
            this.code = HttpStatus.NOT_FOUND.value();
            return this;
        }

        public JsonResultBuilder unauthorized(String msg) {
            this.msg = msg;
            this.code = HttpStatus.UNAUTHORIZED.value();
            return this;
        }


        public JsonResultBuilder total(Long total) {
            this.total = total;
            return this;
        }

        public JsonResultBuilder data(T data) {
            this.results = data;
            return this;
        }

        public JsonResultBuilder message(String msg) {
            this.msg = msg;
            return this;
        }

        public JsonResultBuilder status(Integer code) {
            this.code = code;
            return this;
        }

        public JsonResultBuilder service(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Result build() {
            return new Result<>(this);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getServiceName() {
        return serviceName;
    }

    public T getResults() {
        return results;
    }

    public String getMsg() {
        return msg;
    }

    public Long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", serviceName='" + serviceName + '\'' +
                ", results=" + results +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                '}';
    }


}
