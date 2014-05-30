/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.51-community : Database - share
update by zhanglin 20140530
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`share` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `share`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(200) NOT NULL,
  `article_content` text NOT NULL,
  `article_url` varchar(200) DEFAULT NULL COMMENT '文章头部导图',
  `article_from` varchar(100) DEFAULT NULL,
  `article_author` varchar(100) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0待审核；1 审核通过 ；2已发布；3下架',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `is_original` char(1) NOT NULL COMMENT '0否；1是',
  `is_valid` char(1) NOT NULL COMMENT '0未删除；1删除',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`article_id`,`article_title`,`article_content`,`article_url`,`article_from`,`article_author`,`status`,`update_time`,`create_time`,`is_original`,`is_valid`) values (2,'阿兰·巴迪欧视角下，冷眼看小米“神话”','<p>　　你有没有想过，在小米模式、粉丝经济被吹捧的时候，小米这家公司恰恰是无辜的？钛媒体作者宋大象是一位从事艺术行业的人士，他尝试给我们提供一个独特的视角：即借助艺术分析法来审视“小米热”的现象，在他看来，围绕小米缔造的“神话”，只是用户心理、传播心理的折射和放大。详细分析请耐心阅读，经钛媒体编辑：</p>\r\n<p>　　两个月前，兴之所至注册钛媒体这类的TMT资讯网站的时候，最热切的期望，就是想看看互联网背景下的诸多商业科技现象，有没有比较创新性的视角分析。然后，充满期待地浏览了几天谈论互联网思维、谈论新媒体、谈论大数据、谈论小米文化等等的文章之后，发现大多数的描绘、预测和分析视角都平淡无奇，精彩程度远逊于我所喜爱的当代艺术领域里的分析文章（这位作者是钛媒体上典型的跨界写作者，编辑注）。分析当代艺术，要借助于心理学、社会学、哲学等诸多人文学科的最新理论成果，如果把这些理论成果用于分析当今的商业科技现象，也许读起来会有非常异质和有趣的启发视角。</p>\r\n<p>　　于是，本文就结合当代思想学术明星阿兰。巴迪欧关于存在与事件的思考，拿小米公司开刀，来阐释下小米成功的真相，是如何多元而又难以把握。</p>\r\n<p>　　总的来说，在阿兰·巴迪欧关于“存在”与“事件”的哲学思考中，巴迪欧否认有任何现成的可以指导实践的理论的存在，理论必须是在实践之中生成或者是事后回望时总结的，所谓的真理，只是一种逻辑上的操作，这种操作，是把在具体的情境中所发生的事件的意义，归纳总结成可以理解的真理，不同的事件可以产生不同的真理，这些多样的、完全不是一回事的真理，最后被归结在一个大的名称下。</p>\r\n<p>　　举个例子，比如，说到历史上非常有名的“法国大革命”，这个名称实际上就是对一系列非常不连贯的事件事后诸葛亮式的命名，这些不连贯的事件包括“攻占巴士底狱”“发表人权宣言”“雅各宾派恐怖统治”“热月政变”等等。</p>\r\n<p>　　<strong>究竟是什么成就了小米公司今天的神话？</strong></p>\r\n<p>　　以巴迪欧的视角来观之，实际上是不可穷尽的。但可以肯定的是：小米的神话，恰恰就是这样的一个动态事件不断发生，然后诸多事件不断在消费者和小米的竞争对手陈旧的认识上打洞， 造成巨大的不解和震惊，然后消费者和竞争对手不可理解的东西，后来被总结为可认知可理解的“小米法则”，一个个正面的关于小米的“真理和神话”不断被汇集，最后又全被计在了小米公司帐下，形成了一个神话不断被塑造的过程。</p>\r\n<p>　　小米从成立到今天，在什么情势下历经了哪些“事件”的冲击呢？而哪些事件在既定情势下的发生，超出了小米公司的控制，但最后依然可以被操纵成为小米神话添砖加瓦的神话呢？</p>\r\n<p>　　雷军曾的名言——站在台风口，一头猪都能飞起来。雷军的了不起在于，在国内的手机厂商要么简单优化一下安卓系统来做宣传噱头，要么不自量力甚至想单独开发一个操作系统的时候，他选择了组建正规的团队，在安卓系统基础上持续优化打造MIUI系统。</p>\r\n<p>　　而事实上，小米并不能完全预测MIUI系统将会赢得多么好的口碑，但这绝对值得一试。因为在众多的手机论坛上，各路单兵作战的大神们，总在试图修改或者精简安卓系统，而且很受刷机爱好者们的欢迎。</p>\r\n<p>　　小米的MIUI系统，本就是针对国内手机用户的使用习惯，深度优化过了的安卓系统，那么各路大神为了寻找存在感，当然会非常热衷于对这个已经优化过了的MIUI系统进行测评、分析、评论甚至进一步更改和优化，这样以来小米的MIUI系统会成为各路大神们竞相研究，批评，追捧和试验的对象，原生的谷歌安卓系统，反而会逐渐淡出大神乃至刷机爱好者们的研究视野，使得MIUI系统的口碑效应不断发酵。以至于小米手机还没造出来之前，MIUI就已经成为了人性化体验的操作系统头牌。</p>\r\n<p>　　<strong>雷军擅长制造一种东西，叫做“体面”</strong></p>\r\n<p>　　而在小米手机推出的时候，雷军又像当年的神州电脑一样，成功给小米赋予了“质优价廉”“极致性价比”的产品属性，而对外宣传的名义却诡异地高大上为“为发烧而生”，这样小米手机在消费者心中可以同时满足两种本来是非常矛盾的强烈需求：一是质优价廉，二是“发烧高档”，质优价廉其实是绝大多数消费者心目中最渴盼的产品属性，但是摆在台面上说的时候，质优价廉就构不成一个可供炫耀和谈论的资本，因此，“为发烧而生”就成了一个非常体面的炫耀说辞。</p>\r\n<p>　　然后小米的销售模式创新性采取了自建垂直电商网站然后饥饿营销的模式，这个销售模式在各路媒体的广泛报道和激烈讨论下（不管相关的报道和讨论是正面的还是负面的），使得小米真正具备了“强调个性、标榜叛逆”的布道资格，MIUI系统和小米手机产品本身就不错，然后又在一个互联网传媒社会里，率先借助各种媒体的力量，树立了真正让人觉得非常有个性的公众形象，三股合力的作用可是远远大过1+1+1简单算法的，一个公司让人觉得产品优秀并不罕见，但真正让年轻人觉得你的产品和公司文化有个性，进而产生情感上的集体认同，这才是真正困难的。</p>\r\n<p>　　在探讨小米手机成功的背后，居然很多人忽略了小米可是上过中央电视台新闻联播的，小米公司可是被习主席莅临指导过的，北京市市委书记郭金龙在会见国民党荣誉主席连战的时候可是拿红米手机做国礼相赠的，试问国内任何一家手机生产商的任何宣传，哪一个可以与这两者匹敌呢？虽然新闻联播的口碑一向不太好，但群众对新闻联播内容的怀疑仅限于特定的政绩和官方成绩报道，对于一个纯粹民营公司的正面报道，那可会极大地增强人民群众对这个公司产品的信心的，而恰恰是在这次报道中，小米公司成为了互联网思维、互联网精神的代名词和布道者。</p>\r\n<p>　　实际上冷静思考一下：小米手机究竟有多好，在消费者买到小米手机连续把玩几天之前， 消费者怎么可能确切知道呢？难道每一个购买小米手机的消费者事先都去亲朋好友那借来了小米手机彻底把玩了么？所以小米手机究竟发烧到了何等地步这并不重要，重要的是论坛上，网络上，报纸和杂志上，电视台新闻上，全都是关于小米的正面报道。</p>\r\n<p>　　而且，在互联网经济被广泛讨论的今天，每一次双十一，小米手机的销售额都可以免费占据各大媒体的头版头条，就连在春节联欢晚会晚会上投放的广告，都成为了媒体争相报道和讨论的新闻，就连大学里广告专业编导专业的大学生，都要以小米手机广告为例，分析这则广告的利弊得失。</p>\r\n<p>　　我们需要注意的是：饥饿营销能不能被广泛讨论，小米公司会不会上央视新闻联播，北京市委书记会不会选红米手机送给连战，双十一销售额和春晚前广告能不能以及究竟能引发多大的轰动效应，这些事件并不是小米可以完全预测和控制的。小米公司能做到的，就是在特定的时段和特定的情势里，做出最有效的判断，顺势而为或者引导着情势向自己有利的方向发展，然后通过有着巨大粉丝量的社交媒体的力量，来影响情势、改造情势为小米神话添砖加瓦，无论具体情势中发生的事件，是精心策划的，还是程咬金一样猝不及防半路杀出的，小米总可以把这种相关不相关的事件，转化为更大的有利情势的一部分，这种超常的把握复杂情势和运作事件的能力，的确是非常令人佩服和难以掌握的。</p>\r\n<p>　　这就像艺术界里的马歇尔。杜尚，在商店里买了一只普通的男性小便壶，签名之后送去参加艺术展览，然后被拒绝，然后通过精心的谋划和操作，引发了一系列的关于艺术品定义的争论，最后一举改写了20世纪后半期关于艺术的定义，成为了当代艺术和观念艺术的精神之父，这就是艺术史上非常有名的“理查德。穆特事件”。而小米的神话，也应该从这个角度去予以理解和把握。</p>','http://pic.iresearch.cn/news/2014-05/709ac32f-64a0-4bdc-b32d-42b33e5794c9.jpg\" title=\"001.jpg','钛媒体','宋大象','2','2014-05-30 14:55:48','2014-05-30 14:55:48','0','0'),(3,'罗氏营销得罪“低端用户”后靠噱头能生存吗？','\r\n<p>　　“锤子手机”选在5月20日晚正式发布。按照罗永浩的说法，48小时之后一切都变了，开始有各种“黑”他和“骂”他的。</p><p>　　自2010年开始，演讲就成为罗永浩自我营销推广的一部分，内容完全根据他自己的想法而定，从他的书到培训学校，再到与方舟子的“论战”，今年的主题是他耗费两年研发的锤子手机。尽管罗氏大放厥词称“屌丝们反应十分强烈”，业内仍普遍认为，这款叫价惊人的小众产品，难以逃脱明星phone的共同命运：定价吓人，技术和销量非常一般。</p>\r\n<p>　　从牛博网到老罗英语培训学校，从“冰箱门”事件到研发锤子手机，罗氏营销一直都采用非常极端的手段，抢夺受众眼球。数年之后，更换领域，另类的罗氏营销是否还能奏效?</p>\r\n<p><strong>　　为“制造话题”，罗氏无所不用其极?</strong></p>\r\n<p>　　或粉或黑的文章、段子层出不穷，最广为传播的则是那条，“熬夜看了一遍罗永浩的演讲，他们被老罗做事的每个细节感动着。都已经把钱准备好了，等锤子手机一上市，就买个iPhone6!”无论初衷是什么，段子一下将锤子和iPhone拉到了同一个层级之上。</p>\r\n<p>　　锤子手机也算是手机产品中的异类。一经发布，和产品本身相关的新闻少，罗永浩、方舟子以及各种新奇话题层出不穷。“智商低的人才说我是粉丝营销”、等言论也相当刺激眼球。</p>\r\n<p>　　从2006年创建牛博网开始，罗永浩持续收获着大众关注度和个人影响力，如今他在微博上的粉丝已经超过600万，罗永浩的个人品牌也在“砸冰箱”“论战方舟子”等一次又一次话题的推动中，越来越吸引人眼球。据优酷数据，在此次锤子手机发布会的直播过程中，累计登录观看人次高达274万，最高同时在线近33万人。</p><p><strong>　　靠“人格注意力”能贩卖出好价钱?</strong></p>\r\n<p>　　和小米的饥饿营销不同，锤子手机几乎可以算是打着罗氏营销的旗号。新媒体营销专家李东楼将罗氏营销的核心，归于罗永浩的意见领袖作用、微博上持续的营销造势以及在传播策略上满足媒体的需求。而这三点的核心都离不开罗永浩本人。</p>\r\n<p>　　李东楼认为，在锤子手机的背后，罗永浩贩卖的其实是“人格注意力”。“买锤子手机的人一定是认同罗永浩价值观的人，但他们一定心里也明白，自己花的3000元里一部分是付给手机本身的，另一部分则是为了&quot;供养&quot;。”</p>\r\n<p>　　依靠人格注意力就能卖个好价钱?高定价也因此成为最大的争议点。IT观察家冀勇庆在其节目中表示，由于目前国产手机的品牌影响力还没有上去，因此即使是顶级配置的国产手机，一般情况下也只敢将价格定在2000-2500元这个区间，基本没有将价格定在3000元以上的，那里是苹果和三星</a>的地盘。</p>\r\n<p>　　价格提升到一定程度的时候，需求量必然会急剧下降，因此冀勇庆认为，锤子手机也无法避免这样的规律：一般的大众消费者是不可能花3000元买一部国产的新进入的品牌手机的。</p>\r\n<p>　　和小米的饥饿营销不同，锤子手机几乎可以算是打着罗氏营销的旗号。新媒体营销专家李东楼将罗氏营销的核心，归于罗永浩的意见领袖作用、微博上持续的营销造势以及在传播策略上满足媒体的需求。李东楼认为，在锤子手机的背后，罗永浩贩卖的其实是“人格注意力”。“买锤子手机的人一定是认同罗永浩价值观的人，他们花的3000元里一部分是付给手机本身的，另一部分则是为了&quot;供养&quot;。”</p>\r\n<p>　　罗永浩在此前接受凤凰科技专访时谈及他的理想状态：当我完全不用这些社会化网络以后，我除了一年一次的发布会，基本上就不出来露面了，然后大家就见不着这个人了。每年开发布会出来一次，然后每年卖个几百万或者上千部手机，神秘大亨挺好的。</p>\r\n<p>　　但只要罗永浩个人品牌的知名度还是远远超过企业本身、消费者关注罗永浩的言论多过于产品的价值时，他和神秘大亨还是相距甚远。</p>\r\n<p><strong>　　明星phone定价高，技术和销量一般</strong></p>\r\n<p>　　锤子手机究竟能卖多少部?据其公布的数据称，在锤子手机发布会结束后近48小时，T 1的预订单数超过5万，一周内，预订数量是6万多。</p>\r\n<p>　　对于罗永浩计划做手机一事，锤子手机自称A轮获7000万元、B轮获1 .8亿元的融资。此前锤子科技在融资中的估值达4 .7亿元人民币。投资背后的逻辑并不复杂，凭着罗永浩的个人影响力，卖20万到30万部已经能收回成本，卖50万部应该不成问题，但现在看来能否达到这个数字还很难说。</p>\r\n<p>　　业内人士分析，罗永浩的目标用户群体依旧是他的粉丝群体，这不难让人联想到此前掀起一阵热潮的明星phone，包括崔健的“蓝色骨头”，韩庚的“庚phone”，汪峰的“怒放”，共同特点是定价普遍偏高，但技术和销量却非常一般。</p>\r\n<p>　　不同于明星定制手机的赚快钱，罗永浩的野心绝不仅仅止于此，但也因此错过了圈住粉丝群体的最佳时机。早在2012年4月，锤子手机的概念就已经提出，但直到去年春季官方才发布锤子操作系统，而真正到手机上市，一晃两年过去，罗永浩的“慢”是否能适应得了手机市场变幻之“快”?</p>\r\n<p>　　冀勇庆认为，罗永浩能为锤子手机争取到一部分与他脾性相投，同时也有购买力的消费者，但要走大众市场，也必须得有大众渠道，仅仅依靠锤子手机自己的网站是无法支持高达几十万甚至上百万手机订单的。从目前锤子手机的准备情况、资金情况等多个方面判断，根本无法支持这么大的采购量和销售量。</p>\r\n<p>　　除此之外，昂贵的小众情怀能否被大众市场所接受，真正做到长线发展，也正是罗永浩需要担心的。就像他自己所说的那样，“只要卖得好什么都不愁，如果我卖得特别差的话，所有的都白扯”。</p>','http://pic.iresearch.cn/news/201405/c5d4adb2-c08e-44d1-8173-de4555a816bd.jpg','南方都市报','余玥','2','2014-05-30 15:01:39','2014-05-30 15:01:39','0','0'),(4,'文化部清理手游市场 对豌豆荚等进行处罚','\r\n<p style=\"text-indent:2em;\">文化部日前发布第20批违法违规互联网文化活动查处结果。截至5月底，相关文化市场综合执法机构已经对点名的“豌豆荚”等14家手机游戏平台中的10家完成了行政处罚，累计罚款11.3万元，并责令相关平台下架违规游戏，同时进行全面整改；其他4家也已立案查处，近期将完成行政处罚程序。</p><p style=\"text-indent:2em;\">继2013年底对手机网络游戏市场进行集中清理整治后，文化部于今年4月再次发布违法违规互联网文化活动黑名单，重点清理手机游戏平台运营含有宣扬色情内容的网络游戏，或在网络游戏推广和宣传过程中含有宣扬色情的内容。这是近年来文化部发布的第20批违法违规互联网文化活动黑名单，被点名的企业包括：北京卓易讯畅科技有限公司(平台名称：豌豆荚)、福建博瑞网络科技有限公司(平台名称：91手机助手)等14家手机游戏平台，以及趣游科技集团有限公司等8家网游公司。违规游戏产品包括“苍老师打飞机”、“蹂躏女优”等41款。</p>\r\n<p style=\"text-indent:2em;\">文化部文化市场司相关负责人说，经过一段时间的集中整治，目前“手游”市场清理已取得阶段性成效。他同时表示，随着“手游”迅速成为互联网的“金矿”，一些游戏开发商在短期利益的驱使下，研发大量没有技术含量的小型网络游戏，并将含有宣扬色情等内容作为噱头，吸引玩家下载使用；一些手机游戏平台对上架游戏内容把关不严，对明显宣扬色情的游戏视而不见，导致违规游戏广泛传播，对社会风气及青少年健康成长的环境产生了恶劣影响。</p>\r\n<p style=\"text-indent:2em;\">据了解，下一步，文化部将采取各种措施继续督促指导各地综合执法队伍加大“手游”市场监管力度，持续发布违法违规互联网活动黑名单，严厉打击游戏内容本身或宣传推广过程中含宣扬色情等违规行为，维护社会公共道德和青少年合法权益。</p>','http://pic.iresearch.cn/news/2014-05/709ac32f-64a0-4bdc-b32d-42b33e5794c9.jpg\" title=\"001.jpg','新华网','佚名','2','2014-05-30 15:08:56','2014-05-30 15:08:56','0','0');

/*Table structure for table `article_copy` */

DROP TABLE IF EXISTS `article_copy`;

CREATE TABLE `article_copy` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(200) NOT NULL,
  `article_content` text NOT NULL,
  `article_url` varchar(200) DEFAULT NULL,
  `article_from` varchar(100) DEFAULT NULL,
  `article_author` varchar(100) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0待审核；1 审核通过 ；2已发布；3下架',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `is_original` char(1) NOT NULL COMMENT '0否；1是',
  `is_valid` char(1) NOT NULL COMMENT '0未删除；1删除',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article_copy` */

/*Table structure for table `article_hot` */

DROP TABLE IF EXISTS `article_hot`;

CREATE TABLE `article_hot` (
  `hot_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `reprint_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article_hot` */

/*Table structure for table `article_kind` */

DROP TABLE IF EXISTS `article_kind`;

CREATE TABLE `article_kind` (
  `article_kind_id` int(11) NOT NULL AUTO_INCREMENT,
  `kind_name` varchar(30) NOT NULL,
  `kind_pinyin` varchar(30) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0无效；1有效',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `parent_kind_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`article_kind_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `article_kind` */

insert  into `article_kind`(`article_kind_id`,`kind_name`,`kind_pinyin`,`status`,`update_time`,`create_time`,`admin_id`,`parent_kind_id`) values (1,'移动互联网','yidong','1','2014-05-30 13:35:44','2014-05-30 13:33:10',0,0),(2,'互联网创业','chuangye','1','2014-05-30 13:34:36','2014-05-30 13:34:36',0,0),(3,'微信营销','weixin','1','2014-05-30 13:34:54','2014-05-30 13:34:54',0,0),(4,'金融理财','licai','1','2014-05-30 13:35:17','2014-05-30 13:35:17',0,0),(5,'手游资讯','shouyou','1','2014-05-30 13:44:57','2014-05-30 13:44:57',0,0);

/*Table structure for table `article_kind_rel` */

DROP TABLE IF EXISTS `article_kind_rel`;

CREATE TABLE `article_kind_rel` (
  `article_kind_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `article_kind_id` int(11) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0无效；1有效',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_kind_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `article_kind_rel` */

insert  into `article_kind_rel`(`article_kind_rel_id`,`article_id`,`article_kind_id`,`status`,`update_time`,`create_time`,`admin_id`) values (2,2,1,'1','2014-05-30 14:55:48','2014-05-30 14:55:48',0),(3,3,1,'1','2014-05-30 15:01:39','2014-05-30 15:01:39',0),(4,4,5,'1','2014-05-30 15:08:56','2014-05-30 15:08:56',0);

/*Table structure for table `article_label` */

DROP TABLE IF EXISTS `article_label`;

CREATE TABLE `article_label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(20) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0未启用；1启用',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `article_label` */

insert  into `article_label`(`label_id`,`label_name`,`status`,`create_time`) values (1,'移动','1','2014-05-30 13:38:10'),(2,'联通','1','2014-05-30 13:38:18'),(3,'3G','1','2014-05-30 13:38:24'),(4,'在线支付','1','2014-05-30 13:38:38'),(5,'网购','1','2014-05-30 13:38:50'),(6,'微信营销','1','2014-05-30 13:39:32'),(7,'微信运营','1','2014-05-30 13:39:41'),(8,'锤子手机','1','2014-05-30 13:39:54'),(9,'小米','1','2014-05-30 13:40:08'),(10,'360','1','2014-05-30 13:40:15'),(11,'腾讯','1','2014-05-30 13:40:23'),(12,'阿里巴巴','1','2014-05-30 13:40:30'),(13,'快播','1','2014-05-30 13:42:19'),(14,'电子商务','1','2014-05-30 13:42:36'),(15,'创业上市','1','2014-05-30 13:42:58'),(16,'淘宝','1','2014-05-30 13:43:18'),(17,'小米','1','2014-05-30 14:40:52'),(18,'饥饿营销','1','2014-05-30 14:41:22'),(19,'手游','1','2014-05-30 15:05:40');

/*Table structure for table `article_label_rel` */

DROP TABLE IF EXISTS `article_label_rel`;

CREATE TABLE `article_label_rel` (
  `article_label_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `label_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`article_label_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `article_label_rel` */

insert  into `article_label_rel`(`article_label_rel_id`,`article_id`,`label_id`,`create_time`) values (1,2,17,'2014-05-30 14:55:48'),(2,2,18,'2014-05-30 14:55:48'),(3,3,8,'2014-05-30 15:01:39'),(4,4,19,'2014-05-30 15:08:56');

/*Table structure for table `article_recommend` */

DROP TABLE IF EXISTS `article_recommend`;

CREATE TABLE `article_recommend` (
  `recommend_id` int(11) NOT NULL AUTO_INCREMENT,
  `recommend_position` char(1) NOT NULL COMMENT '1主页banner；2主页右侧；3详情页;4.列表页面',
  `recommend_sort` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`recommend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article_recommend` */

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `art_article_id` int(11) DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  `attachment_name` varchar(100) NOT NULL,
  `attachment_type` varchar(10) NOT NULL,
  `create_date` datetime NOT NULL,
  `attachmen_sts` char(1) DEFAULT NULL COMMENT '0：无效；1：有效',
  `large` varchar(200) DEFAULT NULL,
  `midsize` varchar(200) DEFAULT NULL,
  `small` varchar(200) DEFAULT NULL,
  `attachment_old_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attachment` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
