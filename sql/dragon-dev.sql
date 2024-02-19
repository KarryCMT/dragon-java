/*
 Navicat Premium Data Transfer

 Source Server         : dragon-dev
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 139.155.77.110:3306
 Source Schema         : dragon-dev

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 19/02/2024 13:59:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dg_collect
-- ----------------------------
DROP TABLE IF EXISTS `dg_collect`;
CREATE TABLE `dg_collect`  (
  `id` bigint(0) NOT NULL COMMENT '收藏ID',
  `post_id` bigint(0) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  `status` int(0) NULL DEFAULT 1 COMMENT '状态：0-未收藏、1-已收藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_collect
-- ----------------------------
INSERT INTO `dg_collect` VALUES (1745817389993431040, 1745764891488292864, 1, 1, '2024-01-12 22:37:56', 1, '2024-01-12 22:37:56', 1, 1);
INSERT INTO `dg_collect` VALUES (1745817685171769344, 1745817622290763776, 2, 1, '2024-01-12 22:39:07', 1, '2024-01-12 22:39:07', 0, 1);

-- ----------------------------
-- Table structure for dg_comment
-- ----------------------------
DROP TABLE IF EXISTS `dg_comment`;
CREATE TABLE `dg_comment`  (
  `id` bigint(0) NOT NULL COMMENT '评论ID',
  `post_id` bigint(0) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `content_type` int(0) NULL DEFAULT 1 COMMENT '评论类型：1：文案2:图片',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `parent_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '父评论ID 如果是1级评论，则为0',
  `answer_id` bigint(0) NULL DEFAULT NULL COMMENT '回复的评论ID',
  `is_author` int(0) NULL DEFAULT 1 COMMENT '是否为文章作者：0-否、1-是',
  `status` int(0) NULL DEFAULT 1 COMMENT '状态：0-被举报、1-正常、2-禁止查看',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_comment
-- ----------------------------
INSERT INTO `dg_comment` VALUES (100, 1745764891488292864, 1, 1, '欢迎大家来评论', 0, NULL, 1, 1, 1, '2024-01-11 10:05:08', NULL, NULL, 1);
INSERT INTO `dg_comment` VALUES (101, 1745764891488292864, 2, 1, '来了来了', 100, NULL, 0, 1, 2, '2024-01-11 10:19:37', NULL, NULL, 1);
INSERT INTO `dg_comment` VALUES (102, 1745764891488292864, 3, 1, '你好哇', 100, 101, 0, 1, 3, '2024-01-11 10:20:22', NULL, NULL, 1);
INSERT INTO `dg_comment` VALUES (1746009843321409536, 1745764891488292864, 3, 1, '这是测试评论', 0, NULL, NULL, 1, 1, '2024-01-13 11:22:41', 1, '2024-01-13 11:22:41', 1);
INSERT INTO `dg_comment` VALUES (1746886393210212352, 1745764891488292864, 3, 1, '这是测试评论', 0, NULL, NULL, 1, 1, '2024-01-15 21:25:46', 1, '2024-01-15 21:25:46', 1);

-- ----------------------------
-- Table structure for dg_draft
-- ----------------------------
DROP TABLE IF EXISTS `dg_draft`;
CREATE TABLE `dg_draft`  (
  `id` bigint(0) NOT NULL COMMENT '草稿ID',
  `post_id` bigint(0) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态：0-未保留、1-已保留',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '草稿表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_draft
-- ----------------------------
INSERT INTO `dg_draft` VALUES (1745982986064629760, 1745982986056241152, 1, 1, 1, '2024-01-13 09:35:57', 1, '2024-01-13 09:35:57', 1);
INSERT INTO `dg_draft` VALUES (1745984614377328641, 1745984614377328640, 2, 1, 2, '2024-01-13 09:42:26', NULL, NULL, 1);

-- ----------------------------
-- Table structure for dg_follow
-- ----------------------------
DROP TABLE IF EXISTS `dg_follow`;
CREATE TABLE `dg_follow`  (
  `id` bigint(0) NOT NULL COMMENT '关注ID',
  `followed_id` bigint(0) NOT NULL COMMENT '被关注用户ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `status` int(0) NULL DEFAULT 1 COMMENT '状态：0-未关注、1-已关注',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_follow
-- ----------------------------
INSERT INTO `dg_follow` VALUES (1745839380670582784, 2, 1, 1, 1, '2024-01-13 00:05:19', 1, '2024-01-13 00:05:19', 1);
INSERT INTO `dg_follow` VALUES (1745840335327727616, 1, 2, 1, 1, '2024-01-13 00:09:07', 1, '2024-01-13 00:09:07', 0);

-- ----------------------------
-- Table structure for dg_foot_mark
-- ----------------------------
DROP TABLE IF EXISTS `dg_foot_mark`;
CREATE TABLE `dg_foot_mark`  (
  `id` bigint(0) NOT NULL COMMENT '足迹ID',
  `post_id` bigint(0) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '足迹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_foot_mark
-- ----------------------------
INSERT INTO `dg_foot_mark` VALUES (1745992477543895040, 1745817622290763776, 3, 3, '2024-01-12 10:13:39', 3, '2024-01-13 10:13:39', 1);
INSERT INTO `dg_foot_mark` VALUES (1745992585471725568, 1745817622290763771, 3, 3, '2024-01-11 10:14:06', 3, '2024-01-13 10:14:06', 1);
INSERT INTO `dg_foot_mark` VALUES (1745992609484115968, 1745817622290763778, 3, 3, '2024-01-10 10:14:12', 3, '2024-01-13 10:14:12', 1);
INSERT INTO `dg_foot_mark` VALUES (1745992641121751040, 1745817622290763779, 3, 3, '2024-01-13 10:14:19', 3, '2024-01-13 10:14:19', 1);

-- ----------------------------
-- Table structure for dg_like
-- ----------------------------
DROP TABLE IF EXISTS `dg_like`;
CREATE TABLE `dg_like`  (
  `id` bigint(0) NOT NULL COMMENT '主键id',
  `post_id` bigint(0) NOT NULL COMMENT '帖子id',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态：0-已禁用、1-已启用',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_like
-- ----------------------------
INSERT INTO `dg_like` VALUES (1754691739429507072, 1745984614377328640, 100, 1, '2024-02-06 10:21:26', 1, '2024-02-06 10:21:26', 1, 0);

-- ----------------------------
-- Table structure for dg_post
-- ----------------------------
DROP TABLE IF EXISTS `dg_post`;
CREATE TABLE `dg_post`  (
  `id` bigint(0) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `topic_id` bigint(0) NOT NULL COMMENT '话题ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帖子内容',
  `pictures` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帖子图片',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态：0-草稿、1-待审核、2-已通过、3-已禁用',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '帖子表（首页-搜索）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_post
-- ----------------------------
INSERT INTO `dg_post` VALUES (10001, 100, 20001, '学习Java', '学习Java', '1757036979087872000,1757037519276478464,1757038005635387392', NULL, 0, 1, '2024-01-11 09:45:22', NULL, NULL, 1);
INSERT INTO `dg_post` VALUES (1745764891488292864, 100, 1745669167723450368, '咕咚咕咚', '点点滴滴aaaa', '1757036979087872000,1757037519276478464,1757038005635387392', NULL, 1, 1, '2024-01-12 19:09:20', 1, '2024-01-12 19:10:11', 0);
INSERT INTO `dg_post` VALUES (1745817622290763776, 100, 1745669167723450368, '学习springBoot', '坚持就是胜利！', '1757036979087872000,1757037519276478464,1757038005635387392', NULL, 1, 1, '2024-01-12 22:38:52', 1, '2024-01-12 22:38:52', 1);
INSERT INTO `dg_post` VALUES (1745982986056241152, 100, 1745669167723450368, '学习哇', '哈哈哈哈哈哈哈', '1757036979087872000,1757037519276478464,1757038005635387392', NULL, 0, 1, '2024-01-13 09:35:57', 1, '2024-01-13 09:35:57', 1);
INSERT INTO `dg_post` VALUES (1745984614377328640, 100, 1745669167723450368, '程序员啊', '干活干活干活', '1757036979087872000,1757037519276478464,1757038005635387392', NULL, 0, 2, '2024-01-13 09:42:26', 2, '2024-01-13 09:42:26', 1);
INSERT INTO `dg_post` VALUES (1755046058238545920, 100, 20001, '软考里系统架构设计师容易过吗，有人考过没', '难是真的难，管用也是真管用，我哥们考了这个，现在是组里最年轻的架构师，薪资高我好几倍，昔日义子变成这样，真比sha了我还难受。', '1757036979087872000,1757037519276478464,1757038005635387392', '', 1, 1, '2024-02-07 09:49:22', 1, '2024-02-07 09:49:22', 1);
INSERT INTO `dg_post` VALUES (1755131317160382464, 100, 1745668690461986816, '抖音到底把爱情观扭曲成了什么样？', '银行程序员不请自来。首先这句话“银行科技岗是程序员最好的就业选择”肯定不对，任何就业选择都是有利有弊，没有最好的选择，只有最适合的选择。银行科技岗在我2019年参加秋招时并不算特火爆，但是在如今的2024年，互联网寒冬已经持续了两年，越来越多的人选择了加入银行科技岗，大家看我的认证也知道，我是985计算机科班的硕士，我当年秋招手里有几个互联网大厂40w+的Offer，还有一篇CCF A类顶会，但是在最后，我还是选择了银行科技岗。接下来来给大家讲讲我为什么选择到银行当程序员。首先我们要清楚什么是银行科技岗。', '1757036979087872000,1757037519276478464,1757038005635387392', '', 1, 1, '2024-02-07 15:28:09', 1, '2024-02-07 15:28:09', 1);
INSERT INTO `dg_post` VALUES (1755132470841446400, 100, 20001, '孩子被抢，父亲把人贩子打死，是不是正当防卫？', '去年在石景山游乐园，老婆奶茶喝多了去撒尿了。我找了个公园的凳子坐下了；稍后，旁边来了个可爱的小姑娘，四五岁的样子。扎着羊角辫，就自己一个人，吃着双球冰淇凌，在我旁边2米外的椅子上坐下，而后，来了一个20多岁的帅哥，对小姑娘说：你妈在那边等你呢，你在这坐着干嘛？我以为是他们家人，我也没在意。结果小姑娘说:你是谁呀？\n\n作者：汐颜君\n链接：https://www.zhihu.com/question/425633815/answer/3374037727\n来源：知乎\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。', '1757036979087872000,1757037519276478464,1757038005635387392', '', 1, 1, '2024-02-07 15:32:44', 1, '2024-02-07 15:32:44', 1);
INSERT INTO `dg_post` VALUES (1755132653453053952, 100, 1745668690461986816, '为何男女比例失调下我依然没有男朋友？', '一个冷笑话。如果在相亲中，一个男性身高，相貌，收入，家庭，学历都让女生满意，那么，恭喜你！男的肯定没看上你？如今，男朋友成了高配产品。一位三十多岁的女生（她坚持称自己是女生 ）对我说：阿姨，我想找一个一米七五以上（中国男人的平均身高是一米七）中产阶层家庭（中国大城市人口不超过一亿。能称为中产的不超过百分之二十）研究生学历（中国去年有百分之七十的学生不参加高考）年薪吗……没有百万，税后五十也行！（天哪，税后五十是外企中层的工资了。\n\n作者：郁练级\n链接：https://www.zhihu.com/question/263808314/answer/2422438053\n来源：知乎\n著作权归作者所', '1757036979087872000,1757037519276478464,1757038005635387392', '', 1, 1, '2024-02-07 15:33:28', 1, '2024-02-07 15:33:28', 1);
INSERT INTO `dg_post` VALUES (1757038156349313024, 100, 20001, '这是学习的哇这是学习的哇', '阿斯顿啊实打实的阿斯顿阿斯顿阿三阿斯顿啊实打实的阿斯顿阿斯顿阿三阿斯顿啊实打实的阿斯顿阿斯顿阿三阿斯顿啊实打实的阿斯顿阿斯顿阿三阿斯顿啊实打实的阿斯顿阿斯顿阿三', '1757036979087872000,1757037519276478464,1757038005635387392', '', 1, 1, '2024-02-12 21:45:15', 1, '2024-02-12 21:45:15', 1);

-- ----------------------------
-- Table structure for dg_topic
-- ----------------------------
DROP TABLE IF EXISTS `dg_topic`;
CREATE TABLE `dg_topic`  (
  `id` bigint(0) NOT NULL COMMENT '话题ID',
  `topic_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '话题名称',
  `topic_cover` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '话题ICON',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `creator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '字段创建时间',
  `updator_id` bigint(0) NULL DEFAULT NULL COMMENT '字段更新者ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '字段更新时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态：0-已禁用、1-已启用',
  `flag` int(0) NULL DEFAULT 1 COMMENT '标志：0-已删除、1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dg_topic
-- ----------------------------
INSERT INTO `dg_topic` VALUES (20001, '二手交易', 'https://www.baidu.com', NULL, 1, '2024-01-11 09:46:34', NULL, NULL, 1, 1);
INSERT INTO `dg_topic` VALUES (1745668690461986816, '相亲圈', 'http://www.163.com', NULL, 1, '2024-01-12 12:47:03', 1, '2024-01-12 12:47:03', 1, 1);
INSERT INTO `dg_topic` VALUES (1745669167723450368, '娱乐圈', 'http://www.163.com', NULL, 1, '2024-01-12 12:48:57', 1, '2024-01-12 12:52:03', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
