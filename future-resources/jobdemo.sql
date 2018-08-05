/*
Navicat PGSQL Data Transfer

Source Server         : localhost
Source Server Version : 90609
Source Host           : localhost:5432
Source Database       : future
Source Schema         : jobdemo

Target Server Type    : PGSQL
Target Server Version : 90609
File Encoding         : 65001

Date: 2018-08-05 23:04:55
*/


-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS "jobdemo"."schedule_job";
CREATE TABLE "jobdemo"."schedule_job" (
"id" int8 NOT NULL,
"class_name" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"cron_expression" varchar(255) COLLATE "default",
"description" varchar(255) COLLATE "default",
"job_group" varchar(255) COLLATE "default",
"job_name" varchar(255) COLLATE "default",
"last_update_time" timestamp(6),
"trigger_group" varchar(255) COLLATE "default",
"trigger_name" varchar(255) COLLATE "default",
"pause" bool
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "jobdemo"."schedule_job" IS '计划任务表';
COMMENT ON COLUMN "jobdemo"."schedule_job"."id" IS '主键ID';
COMMENT ON COLUMN "jobdemo"."schedule_job"."class_name" IS '类名';
COMMENT ON COLUMN "jobdemo"."schedule_job"."create_time" IS '创建时间';
COMMENT ON COLUMN "jobdemo"."schedule_job"."cron_expression" IS '任务表达式';
COMMENT ON COLUMN "jobdemo"."schedule_job"."description" IS '描述信息';
COMMENT ON COLUMN "jobdemo"."schedule_job"."job_group" IS 'Job组';
COMMENT ON COLUMN "jobdemo"."schedule_job"."job_name" IS 'Job名称';
COMMENT ON COLUMN "jobdemo"."schedule_job"."last_update_time" IS '最后更新时间';
COMMENT ON COLUMN "jobdemo"."schedule_job"."trigger_group" IS 'trigger组';
COMMENT ON COLUMN "jobdemo"."schedule_job"."trigger_name" IS 'trigger名称';
COMMENT ON COLUMN "jobdemo"."schedule_job"."pause" IS '暂停标志';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table schedule_job
-- ----------------------------
ALTER TABLE "jobdemo"."schedule_job" ADD PRIMARY KEY ("id");
