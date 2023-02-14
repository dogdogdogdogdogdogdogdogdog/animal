-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: animal
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal_funeral`
--

DROP TABLE IF EXISTS `animal_funeral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animal_funeral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(64) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `area` varchar(12) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `link` varchar(256) DEFAULT NULL,
  `service` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_funeral`
--

LOCK TABLES `animal_funeral` WRITE;
/*!40000 ALTER TABLE `animal_funeral` DISABLE KEYS */;
INSERT INTO `animal_funeral` VALUES (156,'翡翠森林(殷艾寵物森林)台北門市','02-24929163','台北市','台北市中山區新生北路一段45號','https://www.in-lovepet.com/','民間機構提供寵物禮儀服務'),(157,'台北市動物保護處','02-87913254','台北市','台北市內湖區潭美街852號','https://www.tcapo.gov.taipei/cp.aspx?n=1F425FA2E64328D4','政府機關提供寵物火化服務'),(158,'北新莊寵物安樂園','02-26220449','新北市','新北市淡水區北新路一段603號','https://www.psc0449.com/','民間機構提供寵物禮儀服務'),(159,'玖隆寵物生命禮儀','0800-899996','新北市','新北市淡水區忠寮里破瓦厝子1-10號','https://9pet.com.tw/','民間機構提供寵物禮儀服務'),(160,'愛物園寵物紀念館','02-86262827','新北市','新北市淡水區埔子頂16-1號','https://www.facebook.com/lovepet168/','民間機構提供寵物禮儀服務'),(161,'淺水灣寵物天堂','02-26369293','新北市','新北市三芝區南勢崗2之7號','http://www.petstw.tw/','民間機構提供寵物禮儀服務'),(162,'葺葺花園','02-77516083','新北市','新北市汐止區八連路二段329巷130號','https://fluffy.tw/','民間機構提供寵物禮儀服務'),(163,'康寧寵物安樂園','02-26641826','新北市','新北市深坑區北深路三段93巷51號','https://www.kanning.com.tw/','民間機構提供寵物禮儀服務'),(164,'萬里福田寵物城堡','0800-0068888','新北市','新北市萬里區大坪1-1號','https://www.petscastle.com.tw/','民間機構提供寵物禮儀服務'),(165,'懷恩寵物安樂園','02-26093133','新北市','新北市林口區大水湳路69號','https://petparadise.com.tw/','民間機構提供寵物禮儀服務'),(166,'慈愛寵物樂園','0921-628958','新北市','新北市三峽區介壽路三段172巷30號','http://www.ciai.com.tw/','民間機構提供寵物禮儀服務'),(167,'新北市政府動物保護防疫處','02-29596353','新北市','新北市板橋區四川路一段157巷2號','https://www.ahiqo.ntpc.gov.tw/cht/index.php?code=list&flag=detail&ids=15&article_id=346','政府機關提供寵物火化服務'),(168,'星旅程寵物生命有限公司','0930-567840','宜蘭縣','宜蘭縣宜蘭市宜蘭縣冬山鄉信光路95巷19號','https://trunslu.com/star-journey/','民間機構提供寵物禮儀服務'),(169,'譽馨寵物天堂','0900-765862','宜蘭縣','宜蘭縣宜蘭市民權里自強路11巷14號1樓','https://www.facebook.com/profile.php?id=100064088197395','民間機構提供寵物禮儀服務'),(170,'宜蘭縣動植物防疫所','03-9602350','宜蘭縣','宜蘭縣五結鄉成興村利寶路60號','https://animal.e-land.gov.tw/','政府機關提供寵物火化服務'),(171,'愛的寵物天堂','03-5297230','新竹市','新竹市五福路二段1450號','https://www.love-pet.com.tw/','民間機構提供寵物禮儀服務'),(172,'芯寶寵物樂園','03-5293399','新竹縣','新竹縣寶山鄉三峰路2段329巷56號','https://www.sinbao.com.tw/','民間機構提供寵物禮儀服務'),(173,'天使驛站','04-22377738','台中市','台中市北區青島路三段12號','https://www.facebook.com/ANGEL0STATION','民間機構提供寵物禮儀服務'),(174,'寵兒永愛園','0955-833690','台中市','台中市北區太原南一街10號','http://www.petforeverlove.com/','民間機構提供寵物禮儀服務'),(175,'翡翠森林(殷艾寵物森林)台中門市','0910-912991','台中市','台中市北屯區建和路二段296號','https://www.in-lovepet.com/','民間機構提供寵物禮儀服務'),(176,'永安寵物樂園','04-24373339','台中市','台中市北屯區軍功里東山路一段253號','https://www.facebook.com/profile.php?id=100063612322656','民間機構提供寵物禮儀服務'),(177,'深耕寵物森林園區','04-22281391','台中市','台中市西區五權路2之2號1樓','https://www.sgforest.com.tw/','民間機構提供寵物禮儀服務'),(178,'寵兒安樂園','04-22712425','台中市','台中市大里區仁堤路368號','http://www.beloved-land.com/','民間機構提供寵物禮儀服務'),(179,'周家花園寵物禮儀','0920-775095','台中市','台中市豐原區三豐路2段549巷178弄148號','http://www.chouspet.com/','民間機構提供寵物禮儀服務'),(180,'彼岸森林寵物紀念園區','04-25159171','台中市','台中市豐原區東陽路獅座巷7號','https://www.facebook.com/HiganPetto/','民間機構提供寵物禮儀服務'),(181,'台富動物處理中心','0932-632198','台中市','台中市霧峰區復興路二段9號','https://www.xn--hhru84eo9aw23b.tw/','民間機構提供寵物禮儀服務'),(182,'台中市寵物植存紀念園區','04-25588024','台中市','台中市后里區堤防路370號','https://www.animal.taichung.gov.tw/','政府機關提供寵物殯葬服務'),(183,'台中市動物保護防疫處','04-23869420','台中市','台中市南屯區萬和路一段28-18號','https://www.animal.taichung.gov.tw/','政府機關提供寵物火化服務'),(184,'嘉義市動物保護教育園區','05-2168661','嘉義市','嘉義市東區芳草里彌陀路31號','https://ccap.chiayi.gov.tw/cp.aspx?n=8553','政府機關提供寵物火化服務'),(185,'慈成寵物新天地','0800-520090','嘉義縣','嘉義縣民雄鄉東湖村東勢湖210號','http://607.go168.com.tw/','民間機構提供寵物禮儀服務'),(186,'寶貝天堂','0960-501081','台南市','台南市七股區竹橋里2424號','https://www.babyrecollect.com/','民間機構提供寵物禮儀服務'),(187,'四方寵物','06-2147099','台南市','台南市南區國民路465號','https://www.4sifang.com.tw/','民間機構提供寵物禮儀服務'),(188,'台南市動物防疫保護處','06-6323039','台南市','台南市新營區長榮路一段501號','https://ahipo.tainan.gov.tw/','政府機關提供寵物火化服務'),(189,'後來福務所','07-3707111','高雄市','高雄市鳥松區本館路435之4號','https://petzzz.com.tw/','民間機構提供寵物禮儀服務'),(190,'誼馨園','0800-200621','高雄市','高雄市鳳山區華興街83號','https://yixinyuan.com.tw/','民間機構提供寵物禮儀服務'),(191,'高雄市壽山動物保護教育園區','07-5519059','高雄市','高雄市鼓山區萬壽路350號','https://livestock.kcg.gov.tw/','政府機關提供寵物火化服務'),(192,'高雄市燕巢動物保護關愛園區','07-6051002','高雄市','高雄市燕巢區燕巢區師大路98號','https://livestock.kcg.gov.tw/','政府機關提供寵物火化服務'),(193,'花蓮市動植物防疫所','03-8227431','花蓮縣','花蓮縣花蓮市瑞美路5號','https://acdc.hl.gov.tw/','政府機關提供寵物火化服務'),(194,'澎湖縣家畜疾病防治所','06-9212839','澎湖縣','澎湖縣馬公市西文里118之1號','https://www.phldcc.gov.tw/','政府機關提供寵物火化服務');
/*!40000 ALTER TABLE `animal_funeral` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-13 23:10:08
