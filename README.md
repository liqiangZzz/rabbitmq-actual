# RabbitMQ 实战项目

本项目是一个基于 Spring Cloud 和 RabbitMQ 构建的分布式电商系统示例，演示了如何在微服务架构中使用 RabbitMQ 进行异步通信和服务解耦。

## 项目概述

该项目模拟了一个完整的电商下单流程，包含六个核心微服务模块，通过 RabbitMQ 实现服务间的异步通信，提高系统的响应速度和可扩展性。

## 技术栈

- **核心框架**: Spring Boot 2.3.12.RELEASE
- **微服务治理**: Spring Cloud Hoxton.SR12 + Spring Cloud Alibaba 2.2.6.RELEASE
- **消息中间件**: RabbitMQ
- **服务注册发现**: Nacos
- **持久层框架**: MyBatis Plus 3.4.0
- **数据库**: MySQL 8.0.32
- **项目构建**: Maven 多模块项目
- **JDK 版本**: Java 8

## 项目模块说明

### 1. place-order (下单入口服务)
端口: 8080

核心功能：
- 提供下单接口 [/simulateUserPlaceOrder](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L53-L69)
- 协调其他服务完成下单流程
- 集成 RabbitMQ 实现异步消息发送
- 支持消息确认机制和重试机制
- 实现死信队列和延迟队列处理

### 2. item-stock (库存管理服务)
端口: 8081

核心功能：
- 商品库存扣减 [/decr](file:///Users/Java/project/rabbitmq/rabbitmq-actual/item-stock/src/main/java/com/lq/controller/ItemStockController.java#L20-L28)
- 模拟库存检查逻辑

### 3. order-manage (订单管理服务)
端口: 8082

核心功能：
- 订单创建 [/create](file:///Users/Java/project/rabbitmq/rabbitmq-actual/order-manage/src/main/java/com/lq/controller/OrderManageController.java#L18-L22)
- 订单状态管理
- 死信队列和延迟队列监听处理

### 4. coupon (优惠券服务)
端口: 8083

核心功能：
- 优惠券预扣除 [/coupon](file:///Users/Java/project/rabbitmq/rabbitmq-actual/coupon/src/main/java/com/lq/controller/CouponController.java#L17-L21)

### 5. user-points (用户积分服务)
端口: 8084

核心功能：
- 用户积分扣除 [/deductUserPoints](file:///Users/Java/project/rabbitmq/rabbitmq-actual/user-points/src/main/java/com/lq/controller/UserPointsController.java#L17-L21)
- 幂等性处理防止重复消费

### 6. business (商家服务)
端口: 8085

核心功能：
- 商家通知 [/notify](file:///Users/Java/project/rabbitmq/rabbitmq-actual/business/src/main/java/com/lq/controller/BusinessController.java#L17-L21)

## 核心特性

### 1. 异步通信
通过 RabbitMQ 实现服务间异步通信，降低服务耦合度，提升系统性能。

### 2. 消息可靠性保障
- 生产者确认机制 (publisher-confirm)
- 消息回退机制 (publisher-returns)
- 消费者手动确认 (manual ack)
- 全局异常处理和消息重试机制

### 3. 高级队列模式
- 死信队列 (Dead Letter Queue) - 处理超时订单
- 延迟队列 (Delayed Queue) - 实现延迟任务处理

### 4. 幂等性处理
针对重复消费问题，在用户积分服务中实现了基于数据库的幂等性校验。

### 5. 微服务架构
基于 Spring Cloud 的完整微服务架构，包括服务注册发现、负载均衡等特性。

## 环境准备

### 必要组件
1. **JDK 8**
2. **MySQL 8.0+**
3. **RabbitMQ 3.8+**
4. **Nacos 1.4+**

### 数据库初始化
```sql
CREATE DATABASE rabbitmq;

-- 用户积分幂等表
CREATE TABLE user_points_idempotent (
    id VARCHAR(64) PRIMARY KEY,
    create_time DATETIME
);

-- 订单表
CREATE TABLE tb_order (
    id VARCHAR(64) PRIMARY KEY,
    total DECIMAL(10,2),
    order_state INT
);
```

### 配置修改
根据实际环境修改各模块 `application.yml` 中的相关配置：
- 数据库连接信息
- RabbitMQ 连接信息
- Nacos 注册中心地址

## 启动步骤

1. 启动 Nacos 服务注册中心
2. 启动 RabbitMQ 服务
3. 启动 MySQL 数据库并执行初始化脚本
4. 按顺序启动各微服务模块：
   - item-stock (8081)
   - order-manage (8082)
   - coupon (8083)
   - user-points (8084)
   - business (8085)
   - place-order (8080)
5. 访问下单接口测试：`http://localhost:8080/simulateUserPlaceOrder`

## 测试接口

| 接口路径 | 方法 | 描述 |
|---------|------|------|
| [/simulateUserPlaceOrder](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L53-L69) | GET | 同步下单流程 |
| [/simulateUserPlaceOrder2](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L72-L86) | GET | 异步下单流程(基础版) |
| [/simulateUserPlaceOrder3](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L89-L111) | GET | 异步下单流程(带确认机制) |
| [/simulateUserPlaceOrder4](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L114-L130) | GET | 死信队列延时下单 |
| [/simulateUserPlaceOrder5](file:///Users/Java/project/rabbitmq/rabbitmq-actual/place-order/src/main/java/com/lq/controller/PlaceOrderController.java#L132-L147) | GET | 延迟队列延时下单 |

## 项目亮点

1. **完整的消息处理流程**：从消息生产、路由、存储到消费的全链路实现
2. **多种消息模式**：普通消息、确认消息、死信消息、延迟消息
3. **可靠性保障机制**：确保消息不丢失、不重复消费
4. **微服务最佳实践**：合理的服务拆分和职责划分
5. **生产级代码示例**：可直接参考用于实际项目开发

## 注意事项

1. 各服务需要按依赖关系顺序启动
2. 确保 RabbitMQ 和 MySQL 服务正常运行
3. 根据实际网络环境调整配置文件中的 IP 地址
4. 生产环境中建议增加更完善的安全认证机制

## 扩展方向

1. 集成分布式事务解决方案(如 Seata)
2. 添加监控告警机制(Spring Boot Admin)
3. 实现动态路由和负载均衡策略
4. 增加消息轨迹追踪功能
5. 完善日志收集和分析体系