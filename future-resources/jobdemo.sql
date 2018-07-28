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

Date: 2018-07-28 16:44:07
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

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table schedule_job
-- ----------------------------
ALTER TABLE "jobdemo"."schedule_job" ADD PRIMARY KEY ("id");
