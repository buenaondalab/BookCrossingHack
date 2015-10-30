CREATE DATABASE  IF NOT EXISTS `bookcrossing` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookcrossing`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: bookcrossing
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `registered` datetime DEFAULT NULL,
  `released` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `place_id` bigint(20) DEFAULT NULL,
  `registeredBy_username` varchar(255) DEFAULT NULL,
  `releasedBy_username` varchar(255) DEFAULT NULL,
  `capturedBy_username` varchar(255) DEFAULT NULL COMMENT 'Last user who captured the book.',
  PRIMARY KEY (`id`),
  KEY `FK_f7kggis8sgjaupm1gwckkrusf` (`place_id`),
  KEY `FK_REGISTERED_BY_USER_idx` (`registeredBy_username`),
  KEY `FK_RELEASED_BY_USER_idx` (`releasedBy_username`),
  KEY `FK_CAPTURED_BY_USER_idx` (`capturedBy_username`),
  CONSTRAINT `FK_CAPTURED_BY_USER` FOREIGN KEY (`capturedBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE,
  CONSTRAINT `FK_f7kggis8sgjaupm1gwckkrusf` FOREIGN KEY (`place_id`) REFERENCES `Place` (`id`),
  CONSTRAINT `FK_REGISTERED_BY_USER` FOREIGN KEY (`registeredBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE,
  CONSTRAINT `FK_RELEASED_BY_USER` FOREIGN KEY (`releasedBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i1tqr2ahrk7pblj3vb732a201` (`region_id`),
  CONSTRAINT `FK_i1tqr2ahrk7pblj3vb732a201` FOREIGN KEY (`region_id`) REFERENCES `Region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES (32311,'Bologna',512),(32319,'Rimini',512),(32399,'Venezia',503),(32759,'Genova',620),(32901,'Salerno',675),(32962,'Torino',692),(33051,'Parma',512),(33150,'Forlì',512),(33166,'Cesena',512),(33337,'Pisa',815),(33445,'Brescia',533),(33483,'Portovenere',620),(33539,'Frascati',540),(33675,'Viareggio',815),(34194,'Trieste',731),(34425,'Desenzano del Garda',533),(34786,'Massa',815),(34824,'Agrigento',894),(35059,'Savona',620),(35138,'Ancona',732),(35157,'Avellino',675),(35175,'Perugia',1211),(37035,'Cagliari',1637),(37211,'Cremona',533),(37627,'Sanremo',620),(37628,'Ventimiglia',620),(37843,'Camaiore',815),(38077,'Città di Castello',1211),(38098,'Campi Bisenzio',815),(38394,'Empoli',815),(38803,'Ferrara',512),(39915,'Fisciano',675),(40066,'Cava de\' Tirreni',675),(40404,'Lazise',503),(41990,'Sexten / Sesto',1867),(42237,'Ravenna',512),(42451,'Modena',512),(43734,'Fossano',692),(44389,'Pompei',675),(45804,'Lucca',815),(46062,'Lastra a Signa',815),(46718,'Tolmezzo',731),(48006,'Finale Ligure',620),(50510,'Laives / Leifers',1867),(50947,'San Giovanni Teatino',646),(51245,'Varazze',620),(51797,'Carrara',815),(52473,'Selvazzano Dentro',503),(52477,'Albenga',620),(54388,'Cernusco sul Naviglio',533),(56956,'Grosseto',815),(57146,'Osimo',732),(58833,'Mirandola',512),(61660,'Tarvisio',731),(63814,'Alassio',620),(64307,'Bra',692),(64608,'Alba',692),(68042,'Venaria Reale',692),(68222,'Cattolica',512),(72387,'Carmagnola',692),(80467,'Cavalese',1867),(81033,'Celle Ligure',620),(81194,'Bordighera',620),(82680,'San Lazzaro di Savena',512),(82942,'Roma',540),(90447,'Termoli',1369),(90661,'Forni Avoltri',731),(91890,'Sirmione',533),(95506,'Albavilla',533),(95592,'-- treni, tram, metro e autobus --',533),(96059,'-- strade, autostrade e stazioni di servizio --',1867),(96106,'-- treni, tram, metro, autobus --',540),(96410,'-- da qualche parte in regione (somewhere in the r',620),(97933,'Marcianise',675),(98415,'Gambassi Terme',815),(103095,'Catanzaro',541),(103574,'Albese con Cassano',533),(114529,'Montereale Valcellina',731),(117608,'Nago-Torbole',1867),(118141,'Bobbio',512),(118172,'Paullo',533),(119480,'Milano',533),(119630,'San Lorenzo',620),(123744,'da qualche parte',13130),(135926,'-- Somewhere --',815),(139728,'Sappada',503),(144744,'Rosolina',503),(146880,'Cogorno',620),(150146,'Cles',1867),(152083,'Povegliano',503),(152094,'Sale Marasino',533),(152434,'Ostia',540),(152839,'Villafranca di Verona',503),(152910,'Marone',533),(154462,'Borgoricco',503),(154709,'solbiate comasco',533),(155490,'Monte Grimano Terme',732),(161804,'Bosco Chiesanuova',503),(164069,'Nave',533),(165518,'Bogliaco',533),(165536,'Obervintl/Vandois',1867),(167130,'Floridia',894),(167164,'Varna/ Vahrn',1867),(168629,'Sant\'Arcangelo',882),(169016,'Cigognola',533),(169588,'Ovaro',731),(169731,'Lavagno',503),(169734,'Leonessa',540),(169806,'Calosso',692),(169843,'Costigliole d\'Asti',692),(169863,'Isola del Cantone',620);
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Country` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Country`
--

LOCK TABLES `Country` WRITE;
/*!40000 ALTER TABLE `Country` DISABLE KEYS */;
INSERT INTO `Country` VALUES (1,'USA'),(2,'Canada'),(3,'United Kingdom'),(4,'Australia'),(9,'Mexico'),(10,'Netherlands'),(11,'New Zealand'),(12,'Switzerland'),(13,'Ireland'),(14,'Sweden'),(15,'France'),(16,'Belgium'),(17,'Norway'),(19,'Italy'),(20,'India'),(21,'Finland'),(22,'Russia'),(24,'Croatia'),(25,'Denmark'),(26,'Jamaica'),(28,'Germany'),(29,'Spain'),(30,'Malaysia'),(32,'Japan'),(33,'Greece'),(34,'Argentina'),(35,'Brazil'),(36,'Belize'),(39,'China'),(41,'South Africa'),(42,'Singapore'),(45,'Israel'),(47,'Austria'),(50,'Indonesia'),(62,'Ecuador'),(66,'Luxembourg'),(68,'Caribbean Sea'),(70,'Thailand'),(71,'Poland'),(73,'Portugal'),(75,'Iceland'),(77,'Iran'),(93,'Guatemala'),(95,'Slovenia'),(96,'International Waters'),(101,'Hungary'),(103,'Qatar'),(107,'United Arab Emirates'),(109,'Cambodia'),(120,'Greenland'),(123,'Ukraine'),(128,'Chile'),(130,'Cyprus'),(131,'Turkey'),(134,'Bermuda'),(135,'Turks and Caicos Islands'),(137,'Kazakhstan'),(140,'Cuba'),(141,'Zambia'),(143,'Venezuela'),(145,'Egypt'),(152,'Slovakia'),(164,'Bulgaria'),(171,'Albania'),(176,'Bahrain'),(177,'Belarus'),(180,'Botswana'),(186,'Central African Republic'),(197,'Georgia'),(207,'Laos'),(213,'Lithuania'),(226,'Morocco'),(227,'Namibia'),(228,'Nauru'),(250,'Tunisia'),(253,'Uruguay'),(267,'-- Controlled Releases'),(268,'US Virgin Islands');
/*!40000 ALTER TABLE `Country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Place`
--

DROP TABLE IF EXISTS `Place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Place` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f4us4358rinx2eb9s7o2upi3u` (`city_id`),
  CONSTRAINT `FK_f4us4358rinx2eb9s7o2upi3u` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Place`
--

LOCK TABLES `Place` WRITE;
/*!40000 ALTER TABLE `Place` DISABLE KEYS */;
INSERT INTO `Place` VALUES (36122,'Salerno, Campania Italy',40.68244079999999,14.7680961,'Bar',32901),(37712,'Milano, Lombardia Italy',45.4654219,9.1859243,'Piazza del Duomo',119480),(42311,'Milano, Lombardia Italy',45.4654219,9.1859243,'MM3 - repubblica',119480),(42804,'Bologna, Emilia Romagna Italy',44.494887,11.3426163,'Stazione Ferroviaria',32311),(57168,'Roma, Lazio Italy',41.9027835,12.4963655,'piazza della repubblica',82942),(64433,'via Madonna del mare 7/a Trieste, Friuli Venezia Giulia Italy',45.6472728,13.7669004,'Bar Knulp, via Madonna del mare 7/a',34194),(72630,'Forlì, Emilia Romagna Italy',44.2227398,12.0407312,'- In giro per il mondo',33150),(90473,'Città di Castello, Umbria Italy',43.46397830000001,12.2404869,'Casa mia',38077),(112958,'Portovenere, Liguria Italy',44.0541257,9.836628000000001,'vicino alla chiesa',33483),(122428,'Carrara, Toscana Italy',44.0793245,10.097677,'Biblioteca pubblica di Marina di Carrara',51797),(148488,'Via terracini, 28 Bologna, Emilia Romagna Italy',44.5183771,11.3221206,'AlmaLiberi UniBo - Facoltà di Ingegneria Terracini',32311),(157584,'Via John F. Kennedy 94 Laives / Leifers, Trentino Alto Adige / Südtirol Italy',46.42812,11.33896,'biblioteca don bosco - zona di libera circolazione',50510),(160527,'Venezia, Veneto Italy',45.4408474,12.3155151,'-- In giro per la città',32399),(166590,'Salerno, Campania Italy',40.68244079999999,14.7680961,'stazione ferroviaria',32901),(181892,'via degli Albari 6 Bologna, Emilia Romagna Italy',44.49581999999999,11.34513,'Centro Natura',32311),(189059,'Roma, Lazio Italy',41.9027835,12.4963655,'-- Autobus',82942),(261371,'piazza Vittorio Emanuele 11 Pisa, Toscana Italy',43.7103819,10.3988799,'Bar La Loggia, piazza Vittorio Emanuele 11',33337),(267238,'Roma, Lazio Italy',41.9027835,12.4963655,'Supermercato Coop Via Laurentina',82942),(274484,'Bra, Piemonte Italy',44.6923429,7.8551164,'stazione FS',64307),(275630,'Viale fanin 40 Bologna, Emilia Romagna Italy',44.5135863,11.4064122,'AlmaLiberi UniBo - Facoltà di Agraria',32311),(279098,'Empoli, Toscana Italy',43.7178919,10.9477782,'Palazzetto delle Esposizioni',38394),(296744,'Viareggio, Toscana Italy',43.8657267,10.2513103,'cabina telefonica in passeggiata',33675),(311486,'-- strade, autostrade e stazioni di servizio --, Trentino Alto Adige / Südtirol Italy',46.7340955,11.2888015,'irgendwo auf dem weg, hauptsache hinterm brenner',96059),(319806,'Roma, Lazio Italy',41.9027835,12.4963655,'Black Falcon Pub',82942),(352026,'piazza duomo Parma, Emilia Romagna Italy',44.8031976,10.3304006,'Battistero',33051),(363817,'Pisa, Toscana Italy',43.7228386,10.4016888,'Al prossimo meet up',33337),(364426,'Ventimiglia, Liguria Italy',43.7912366,7.6075864,'Bord de mer',37628),(401609,'Frascati, Lazio Italy',41.8085208,12.6761041,'NA',33539),(434304,'Modena, Emilia Romagna Italy',44.6488366,10.9200867,'Fiera di Modena',42451),(440642,'Pisa, Toscana Italy',43.7228386,10.4016888,'OBCZ - Ospedale Cisanello - Chirurgia e trapianti',33337),(441383,'-- treni, tram, metro, autobus --, Lazio Italy',41.6552418,12.989615,'primo vagone',96106),(441488,'Milano, Lombardia Italy',45.4654219,9.1859243,'Il mago di oz OCZ',119480),(446143,'Bologna, Emilia Romagna Italy',44.494887,11.3426163,'-- In giro per la città',32311),(458626,'via Trieste 49/A Albenga, Liguria Italy',44.0469515,8.2179305,'Libreria Il Fiore d\'Oro',52477),(508961,'Albavilla, Lombardia Italy',45.8029324,9.1880262,'studio medico',95506),(511443,'Milano, Lombardia Italy',45.4654219,9.1859243,'OCZ - Corso Garibaldi',119480),(519293,'panchina di fronte alla Libreria Gulliver Cattolica, Emilia Romagna Italy',43.963318,12.7384318,'Lungomare',68222),(534082,'Largo gemelli 1 Milano, Lombardia 20123 Italy',45.4630043,9.176734,'Università Cattolica',119480),(536379,'Via della Macchiarella 128, Roma Roma, Lazio 00119 Italy',41.7746722,12.3196057,'ARMENIOCZ',82942),(558268,'Strada maggiore 45 Bologna, Emilia Romagna Italy',44.4913135,11.3539218,'Almaliberi UniBo - Facoltà di Scienze politiche',32311),(575016,'Cremona, Lombardia Italy',45.133249,10.0226511,'in centro',37211),(584326,'Via Pietro Gori 33 Pisa, Toscana Italy',43.7127637,10.4027581,'Pizzeria La cereria',33337),(590609,'Milano, Lombardia Italy',45.4654219,9.1859243,'OCZ versodiverso',119480),(599876,'Via Vespucci 140 Pisa, Toscana I-56125 Italy',43.7094765,10.3997606,'OCZ-LoSchiaccia',33337),(600529,'via Thaon De Revel Milano, Lombardia Italy',45.4918842,9.188843,'OCZemotion',119480),(603493,'Via Francesco D\'Ovidio 66 Roma, Lazio 00137 Italy',41.9434581,12.5532909,'Bar D\'Ovidio OCZ',82942),(613643,'Marcianise, Campania Italy',41.0310868,14.2995419,'centro commerciale campania',97933),(614093,'Via Bardolino, 30 Milano, Lombardia 20142 Italy',45.4275564,9.1396696,'Ristorante La Corte della Risaia',119480),(614338,'Albese con Cassano, Lombardia Italy',45.7935879,9.1689894,'OCZ Gocce di caffè',103574),(614412,'via Graziano imperatore 40 Milano, Lombardia 20182 Italy',45.5140705,9.191442900000002,'LAST - Associazione Culturale',119480),(621899,'Ancona, Marche Italy',43.6158299,13.518915,'Università, facoltà di economia',35138),(631080,'via Raffaele de Cesare 98 Roma, Lazio Italy',41.871553,12.5197383,'Fiorditè sala da thè',82942),(633387,'-- treni, tram, metro e autobus --, Lombardia Italy',45.47906709999999,9.8452433,'su un treno',95592),(639305,'via Sabatucci, 2 40138 Bologna Bologna, Emilia Romagna Italy',44.4999128,11.3610201,'OCZ Librovagabondo',32311),(642170,'Milano, Lombardia Italy',45.4654219,9.1859243,'MM1 - Fermata Duomo',119480),(644855,'da qualche parte, -- wild released somewhere in Italy -- Italy',41.87194,12.56738,'non so dove',123744),(646909,'Largo Trombetti, 1 Bologna, Emilia Romagna Italy',44.4964805,11.3514552,'AlmaLiberi UniBo - URP',32311),(647682,'Modena, Emilia Romagna Italy',44.6488366,10.9200867,'OBCZ_dal 5 al 6 - Caffetteria Piazza Settembre',42451),(650116,'Via Filippo Re, 6 Bologna, Emilia Romagna Italy',44.4998908,11.3547691,'AlmaLiberi UniBo - Facoltà di Scienze della Formazione',32311),(650118,'Via Massarenti, 9 Bologna, Emilia Romagna Italy',44.4931032,11.3603882,'AlmaLiberi UniBo - Facoltà di Medicina e Chirurgia',32311),(650124,'Piazza Scaravilli, 2 Bologna, Emilia Romagna Italy',44.497009,11.3523037,'AlmaLiberi UniBo - Facoltà di economia',32311),(650125,'Viale Risorgimento, 2 Bologna, Emilia Romagna Italy',44.4881095,11.3284205,'AlmaLiberi UniBo - Facoltà di Ingegneria Risorgimento',32311),(650439,'via San Vitale n. 15 Bologna, Emilia Romagna Italy',44.4944616,11.3492523,'AlmaLiberi UniBo - Facoltà di Scienze Motorie',32311),(651063,'Via S. Donato 19/2 Ufficio URP Bologna, Emilia Romagna Italy',44.494887,11.3426163,'Almaliberi UniBo - Facoltà di Farmacia -',32311),(651108,'v.le Risorgimento n. 4 Bologna, Emilia Romagna Italy',44.4878217,11.3289371,'AlmaLiberi UniBo - Facoltà di Chimica industiale',32311),(651118,'via zamboni, 33 Bologna, Emilia Romagna Italy',44.4968777,11.3524089,'AlmaLiberi UniBo - Segreterie Zamboni',32311),(651213,'Via Bertoloni, 4 Bologna, Emilia Romagna Italy',44.49860229999999,11.3522111,'AlmaLiberi UniBo - Segreterie Bertoloni',32311),(651597,'via Rovello 2 Milano, Lombardia 20121 Italy',45.46662000000001,9.18468,'Chiostro del teatro Grassi',119480),(651846,'Via Zamboni, 22 Bologna, Emilia Romagna Italy',44.4957842,11.3491717,'AlmaLiberi UniBo - Facoltà di Giurisprudenza –',32311),(651991,'Via Belle Arti 41 Bologna, Emilia Romagna Italy',44.49772,11.3522828,'AlmaLiberi UniBo - Facoltà di Scienze Statistiche',32311),(652406,'Via Zamboni, 38 Bologna, Emilia Romagna Italy',44.4968482,11.3517601,'AlmaLiberi UniBo - Facoltà di Lettere e Filosofia',32311),(652663,'Roma, Lazio Italy',41.9027835,12.4963655,'-- Da qualche parte in città - somewhere in the city',82942),(652796,'Viale Berti Pichat, 6 Bologna, Emilia Romagna Italy',44.5005996,11.3565385,'AlmaLiberi UniBo - Sala studio Morassutti',32311),(653948,'Piazzale della Vittoria, 15 Forlì, Emilia Romagna Italy',44.2175008,12.050569,'AlmaLiberi UniBo - Facoltà di economia',33150),(653964,'Via Mariani, 5 Ravenna, Emilia Romagna Italy',44.41726,12.2019277,'AlmaLiberi UniBo - Facoltà di Conservazione dei beni culturali',42237),(653965,'via angherà, 22 Rimini, Emilia Romagna Italy',44.0620033,12.5699468,'AlmaLiberi UniBo - Facoltà di economia',32319),(653969,'via dei mille, 39 Rimini, Emilia Romagna Italy',44.0650416,12.5680346,'Almaliberi UniBo- Facoltà di Farmacia -',32319),(653970,'via santa chiara, 40 Rimini, Emilia Romagna Italy',44.0571457,12.5685528,'AlmaLiberi UniBo - Facoltà di Lettere e Filosofia',32319),(653971,'corso d\'Augusto, 137 Rimini, Emilia Romagna Italy',44.0605195,12.5672912,'AlmaLiberi UniBo - Facoltà di Scienze della Formazione',32319),(653974,'via Fontanelle, 40 Forlì, Emilia Romagna Italy',44.20002849999999,12.0641577,'AlmaLiberi UniBo - Facoltà di Ingegneria',33150),(653975,'via Giacomo della torre, 1 Forlì, Emilia Romagna Italy',44.2189106,12.0421102,'Almaliberi UniBo - Facoltà di Scienze politiche',33150),(653976,'Corso della Repubblica, 136 Forlì, Emilia Romagna Italy',44.2192856,12.0471529,'AlmaLiberi UniBo - Scuola superiore Lingue moderne per interpreti e traduttori',33150),(653977,'Corso Diaz, 45 Forlì, Emilia Romagna Italy',44.2212878,12.0392549,'AlmaLiberi UniBo - URP',33150),(653979,'via Cattaneo, 17 Rimini, Emilia Romagna Italy',44.061449,12.5698938,'AlmaLiberi UniBo - URP',32319),(653984,'Via Oberdan, 1 Ravenna, Emilia Romagna Italy',44.4159897,12.1954002,'AlmaLiberi UniBo - Facoltà di Giurisprudenza',42237),(653986,'Via Baccarini, 27 Ravenna, Emilia Romagna Italy',44.4127578,12.2004899,'AlmaLiberi UniBo - URP',42237),(654890,'Via Montalti, 69 Cesena, Emilia Romagna Italy',44.1402195,12.2442686,'AlmaLiberi UniBo - URP',33166),(655026,'piazza Goidanich, 60 Cesena, Emilia Romagna Italy',44.1565214,12.2427752,'AlmaLiberi UniBo - Facoltà di Agraria',33166),(655027,'via cavalcavia, 55 Cesena, Emilia Romagna Italy',44.14505860000001,12.2405757,'AlmaLiberi UniBo - Facoltà di Architettura',33166),(655030,'via Genova, 181 Cesena, Emilia Romagna Italy',44.1518826,12.2406458,'AlmaLiberi UniBo - ngegneria Seconda Facoltà',33166),(655032,'Piazza Aldo Moro, 90 Cesena, Emilia Romagna Italy',44.1156476,12.3429664,'AlmaLiberi UniBo - Facoltà di Psicologia',33166),(655676,'via Selmi, 2 Bologna Bologna, Emilia Romagna 40126 Italy',44.4962482,11.35415,'AlmaLiberi UniBo - Dipartimento di Chimica \"G. Ciamician\"',32311),(655687,'P.zza S. Giovanni in Monte 2 Bologna, Emilia Romagna 40124 Italy',44.4911973,11.3484556,'AlmaLiberi UniBo - Dipartimento di Archeologia',32311),(658114,'Via Ranzani, 14 Bologna, Emilia Romagna Italy',44.50118699999999,11.3588024,'AlmaLiberi UniBo - Sala studio Ranzani',32311),(658116,'via Filippo Re, 2 Bologna, Emilia Romagna Italy',44.499219,11.3543718,'AlmaLiberi UniBo - Sala studio Filippo Re',32311),(658117,'via Belmeloro, 14 Bologna, Emilia Romagna Italy',44.4950431,11.3560656,'AlmaLiberi UniBo - Sala studio Belmeloro',32311),(658118,'Via san Petronio vecchio, 17-19 Bologna, Emilia Romagna Italy',44.4899979,11.352357,'AlmaLiberi UniBo - Sala studio San Petronio',32311),(658119,'ia Gandusio, 10 Bologna, Emilia Romagna Italy',44.5043989,11.3563042,'AlmaLiberi UniBo - Sala studio Gandusio',32311),(658580,'ia Zamboni, 36 Bologna, Emilia Romagna Italy',44.49671559999999,11.3516155,'AlmaLiberi UniBo - Biblioteca Discipline Umanistiche',32311),(662941,'Montereale Valcellina, Friuli Venezia Giulia Italy',46.1628864,12.6612142,'Biblioteca',114529),(665105,'via San Martino, 41/45 Pisa Pisa, Toscana 56125 Italy',43.7138545,10.4035572,'OCZ-AL MADINA',33337),(666324,'Via Avvocato Ferrero 80 Carmagnola, Piemonte 10022 Italy',44.8466187,7.712956699999999,'Piumatti Tuttocasa',72387),(666428,'Castello degli Acaja Fossano, Piemonte Italy',44.5497136,7.722322699999999,'Punto BookCrossing Biblioteca',43734),(666902,'Finale Ligure, Liguria Italy',44.1689028,8.3416211,'finalborgo',48006),(668171,'Bologna Via Zamboni 33 Bologna, Emilia Romagna Italy',44.4968777,11.3524089,'Atrio Rettorato',32311),(669949,'Stazione Centrale Pisa C/O Binario Uno Pisa, Italy Pisa, Toscana 56125 Italy',43.713062,10.4017327,'OCZ-PharmastazionePisa',33337),(673126,'Nago-Torbole, Trentino Alto Adige / Südtirol Italy',45.8778243,10.8908516,'Camminare lago',117608),(674232,'Via Filippo Re 8 Bologna, Emilia Romagna Italy',44.5002654,11.3549541,'AlmaLiberi UniBo - Facoltà di lingue',32311),(679037,'Viale Uranio 56 Grosseto, Toscana 58100 Italy',42.7761891,11.098875,'OggettidAltrove',56956),(682978,'VIA ORAZIO VECCHI, 10 Roma, Lazio 00124 Italy',41.7338296,12.355706,'oca giuliva',82942),(684127,'Via Cura, 12 Ravenna Ravenna, Emilia Romagna 48121 Italy',44.41809629999999,12.1935839,'Riflessi Parrucchieri',42237),(684885,'via G. Donizetti, 28 Roma, Lazio 00198 Italy',41.9169501,12.4948242,'AphroditeCaffé',82942),(685882,'via de Pastrovich, 1 Padiglione m - Parco San Giovanni Trieste, Friuli Venezia Giulia 34127 Italy',45.6624713,13.7972715,'Multimediateca M crossing',34194),(686178,'Biblioteca Arti - sez. Spettacolo Via Ostiense, 139 Roma Roma, Lazio 00154 Italy',41.8639134,12.4789201,'romaTRE_SBA_BAA',82942),(686473,'Biblioteca Spettacolo - Sez. Storia dell\'Arte piazza della Repubblica, 10 - Roma Roma, Lazio 00185 Italy',41.9026823,12.4965476,'RomaTRE_SBA_SAA',82942),(686476,'Biblioteca Giuridica Via Ostiense, 161/163 - Roma Roma, Lazio 00154 Italy',41.8624517,12.4790375,'RomaTRE_SBA_GIU',82942),(689443,'Savona, Liguria Italy',44.2975603,8.4645,'Porto',35059),(696189,'Piazza Bracci, 1 San Lazzaro di Savena, Emilia Romagna 40068 Italy',44.470196,11.4080371,'Municipio atrio',82680),(697314,'via dei Gelsi,24/d Roma, Lazio 00171 Italy',41.8882622,12.5644972,'ViviBene via dei Gelsi,24/d',82942),(698687,'via Kennedy San Lazzaro di Savena, Emilia Romagna Italy',44.465257,11.4116682,'Piscina comunale San Lazzaro',82680),(705223,'Agrigento, Sicilia Italy',37.3110897,13.5765475,'Da qualche parte in città',34824),(708217,'Via Ferdinando Ughelli25/27/29 Cell. 388.8780188 Roma, Lazio 00179 Italy',41.8677293,12.5154628,'Associazione Culturale Allegra Tartaruga',82942),(708404,'via Fontanellato,46 Roma, Lazio 00142 Italy',41.8475901,12.4841098,'GreenUtopia',82942),(709219,'Via Repubblica, 11 San Lazzaro di Savena, Emilia Romagna 40068 Italy',44.470502,11.4037019,'AUSL di San Lazzaro',82680),(710763,'Ripa. di porta. ticinese 19 Zona Naviglio Grande Milano, Lombardia 20143 milano Italy',45.45,9.1666667,'StraripaBar',119480),(710903,'via Pietro Moscati 13 Milano, Lombardia 20154 Italy',45.4802324,9.171004800000002,'Aromando bistrot',119480),(711947,'via farini 28/a Bologna, Emilia Romagna 40124 Italy',44.4916709,11.3475382,'Legatoria Montanari',32311),(714477,'P.za S. Pier Damiani c\\o civ. 45 Via Guido Biagi 22 Roma, Lazio 00125 Italy',41.7881947,12.378934,'Casal Bernocchi - Il Libro è Libero - Isola BookCrossing',82942),(714878,'Largo San Leonardo Murialdo, 1 - 00146 Roma Roma, Lazio 00146 Italy',41.8636507,12.479276,'RomaTRE_SBA_TOR',82942),(715133,'Piazza IV Novembre Povegliano, Veneto 37064 Italy',45.34777,10.88122,'Povegliano Veronese',152083),(715418,'Viale Monza 91, Milano Milano, Lombardia Italy',45.49553830000001,9.2193532,'SpazioLuce',119480),(717889,'via Cavour n.26 Ventimiglia, Liguria Italy',43.79177110000001,7.608408499999999,'OBCZ Pappagalli Verdi',37628),(724802,'via Mazzini 83 Sale Marasino (Bs) Sale Marasino, Lombardia 25057 Italy',45.7135992,10.1115422,'Stazione Sale Marasino',152094),(725936,'Via Anastasio II, 416 - Roma Roma, Lazio 000165 Italy',41.9046259,12.4443107,'I Sette Colli B&B',82942),(727739,'Mercato Appagliatore Via dell’Appagliatore snc Ostia, Lazio 00121 Italy',41.7400239,12.2719604,'OCZ Mercato Appagliatore',152434),(727942,'Piazzale della stazione Marone, Lombardia 25054 Italy',45.7345157,10.0948745,'Stazione ferroviaria di Marone',152910),(728785,'Via monte suello 30r Via monte suello 30r Genova, Liguria 16129 Italy',44.4000743,8.950755400000002,'Bar Candispari',32759),(729123,'Piazza San Fedele, 2 Milano, Lombardia 20121 Italy',45.4662276,9.1910876,'OCZ-Magnum-MI',119480),(730050,'Piazzale Aldo Moro 5 Piazzale Aldo Moro 5 Roma, Lazio 00185 Italy',41.9983728,12.0999304,'Biblioteca Biologia Ambientale Sapienza',82942),(730805,'Viale Europa 10 Borgoricco (PD) Borgoricco, Veneto 35010 Italy',45.532023,11.968138,'Biblioteca comunale di Borgoricco',154462),(734252,'Via Fratelli Rosselli Povegliano Veronese (VR) Povegliano, Veneto 37064 Italy',45.3504641,10.8850269,'Villa Balladoro',152083),(735070,'Monte Grimano Terme, Marche Italy',43.866623,12.471661,'Parco \"Bartoli\"',155490),(739235,'Piazza Boccolino, 4 Osimo, Marche 60027 Italy',43.4860604,13.4825626,'Informagiovani Osimo',57146),(739803,'Via Cavour 200 Roma, Lazio 00184 Italy',41.8959946,12.4946036,'Enoteca Trucchi',82942),(740183,'Via dei promontori 7 Ostia, Lazio 00121 Italy',41.7397497,12.2846282,'Yut bookcrossing Lido Nord',152434),(740584,'-- Somewhere --, Toscana Italy',43.7710513,11.2486208,'Private shelf',135926),(741684,'via Paolo de Ralli 3 Trieste, Friuli Venezia Giulia 34128 Italy',45.6583666,13.8003511,'ASS1TS - Dipartimento di Prevenzione',34194),(743232,'Via G. Andrè, Piazza Vega Ostia, Lazio 00122 Italy',41.7264284,12.2947457,'Yut bookcrossing Stella Polare',152434),(750720,'Grosseto, Toscana Italy',42.7635254,11.1123634,'Biblioteca Chelliana',56956),(751548,'Torino, Piemonte Italy',45.070312,7.686856499999999,'Area 12',32962),(751722,'Torino, Piemonte 10135 Italy',45.0215567,7.6240389,'Torino - Libreria Maramay',32962),(754827,'Via Satrico 55 Roma, Lazio 00183 Italy',41.8766931,12.5087236,'Mondi Possibili',82942),(755071,'Via montesanto, 41 Roma, Lazio Italy',41.9166316,12.4598325,'BooksBoxM4',82942),(755586,'Roma, Lazio Italy',41.9027835,12.4963655,'Parco della Madonnetta',82942),(758215,'Via Giovanni Pierluigi da Palestrina 41/43 www.facebook.com/pratiurbani Roma, Lazio 00193 Italy',41.9062588,12.4652871,'Prati Urbani',82942),(758280,'Viale Angelico 36b Roma, Lazio 00195 Italy',41.91655,12.45856,'Romatic OBCZ',82942),(758562,'Piazza Caduti Nassyria 2/A, Regione Bagnoli 37 - Albenga (SV) Albenga, Liguria 17031 Italy',44.05937,8.20331,'Centro Commerciale Le Serre',52477),(758599,'Nave, Lombardia Italy',45.5859779,10.2886207,'Villa Zanardelli',164069),(758654,'via del borghetto 75 pisa Pisa, Toscana 56124 Italy',43.7122925,10.4123899,'oczfarmasalute',33337),(759526,'Frazione Pariana 62 Massa, Toscana 54100 Italy',44.0468036,10.1630027,'Pariana - Bar Angeloni',34786),(759656,'Alba, Piemonte Italy',44.7009153,8.0356911,'Bar dello Sporting San Cassiano',64608),(759666,'Via Augusto Romagnoli 6/C Bologna, Emilia Romagna 40136 Italy',44.4754944,11.3687971,'CucitoCafè',32311),(759916,'Cattolica, Emilia Romagna Italy',43.963318,12.7384318,'Centro città',68222),(760806,'Campi Bisenzio, Toscana Italy',43.8259774,11.1276471,'Uci Cinema - Fermata Bus',38098),(761809,'Monte Grimano Terme, Marche Italy',43.866623,12.471661,'Parco Ciacci',155490),(763542,'https://www.facebook.com/pages/PanKaffe-Roma/636436553121966 Via Tiburtina Antica, 8 Roma, Lazio 00185 Italy',NULL,NULL,'PanKaffe Roma OBCZ',82942),(764428,'via di Torre Annunziata 1 Roma, Lazio 00177 Italy',41.8990287,12.5372663,'Municipio Roma V',82942),(766695,'Piazza Chiesa, 20 Bosco Chiesanuova, Veneto 37021 Italy',45.62184,11.02967,'Laboratorio d\'Erbe Sauro',161804),(767826,'Varna/ Vahrn, Trentino Alto Adige / Südtirol Italy',46.7321856,11.6497243,'Kloster Neustift',167164),(768745,'Brescia, Lombardia 25122 Italy',45.5403573,10.215284,'Bar Il Limone',33445),(769567,'Via Matteotti 13 Paullo, Lombardia 26016 Italy',45.4177027,9.4004572,'Studio di Pittura Jan',118172),(769791,'San Giovanni Teatino, Abruzzo Italy',42.4094917,14.2016145,'Panchina - Piazza San Rocco - Sambuceto',50947),(770025,'Roma, Lazio Italy',41.9027835,12.4963655,'Biblioteca Nelson Mandela',82942),(770026,'Lucca, Toscana 55100 Italy',43.8625456,10.4621366,'Coop Sant\'Anna - BiblioCoop: Scaffale GiraLibro',45804),(770302,'Via Andreotto Saracini, 59 Ostia, Lazio 00121 Italy',41.73308,12.2768,'Monkey Business Ostia',152434),(771037,'Corso Vittorio Emanuele 15 Città di Castello, Umbria 06012 Italy',43.455791,12.241451,'Orto&Bio',38077),(772112,'Via G. Sansanelli 19 Sant\'Arcangelo, Basilicata 85037 Italy',40.248696,16.272227,'Associazione Itaca',168629),(772835,'Ostia, Lazio Italy',41.733244,12.278939,'-- Da qualche parte in città!',152434),(773206,'Piazza della Marina, 4 Roma, Lazio 00196 Italy',41.91595,12.47395,'Palazzo Marina',82942),(773799,'P.zza Mercurio Massa, Toscana 54100 Italy',44.0358114,10.1434066,'Galleria biblioteca P.zza Mercurio',34786),(773976,'Via Bassa Tambura Massa, Toscana Italy',44.0458886,10.1485076,'Distretto ASL Massa',34786),(773996,'via lemonia 220 Roma, Lazio 00174 Italy',41.8512221,12.5600382,'Parco degli Acquedotti/punto informazioni',82942),(774635,'Albese con Cassano, Lombardia 22032 Italy',45.7935879,9.1689894,'ComuneAlbeseOBCZ',103574),(774641,'Ronchi Massa, Toscana 54100 Italy',44.0002141,10.1214986,'Ronchi - Parco WWF',34786),(774659,'V.le G.Marconi, 480 Cesena, Emilia Romagna 47521 Italy',44.13705,12.26295,'Ambulatorio Veterinario Luca Pistocchi',33166),(774681,'Milano, Lombardia Italy',45.4654219,9.1859243,'banchina d\'attesa passante Garibaldi',119480),(774698,'CORSO VITTORIO EMANUELE II 390 Cagliari, Sardegna 09123 Italy',39.2206282,9.107058499999999,'pizzeria zahra e yussef',37035),(774737,'Massa, Toscana 54100 Italy',44.03544369999999,10.1393221,'Marina di Massa dove Alberghiero',34786),(774738,'V:le Matteotti,348 Cesena, Emilia Romagna 47521 Italy',44.1396438,12.2464292,'Bagnofido Toelettatura Portofido',33166),(774806,'Piazza Bega Termoli, Molise Italy',42.0005331,14.9952839,'Caffè Casolino',90447),(774819,'Massa, Toscana Italy',44.03544369999999,10.1393221,'Caffè Centrale - Massa (MS)',34786),(774820,'Via Aldo Salvetti, 12 Massa, Toscana 54100 Italy',44.0412983,10.1240569,'Pizzeria Il Merlo',34786),(774880,'Camaiore, Toscana Italy',43.9421402,10.2969083,'Parco Giochi Oberdan',37843),(774885,'via nizza 89r Savona, Liguria 17100 Italy',44.28991,8.45224,'Mare Hotel Savona',35059),(774891,'Mirandola, Emilia Romagna Italy',44.88636049999999,11.0632969,'Note Di Malto',58833),(774892,'Mirandola, Emilia Romagna Italy',44.88636049999999,11.0632969,'Stazione di Servizio Costantin',58833),(774912,'Lazise, Veneto Italy',45.50602019999999,10.7350716,'Spiaggetta',40404),(774914,'via Benedetto Croce, 2 Pisa Pisa, Toscana 56125 Italy',43.7105475,10.4049962,'Liceo Classico Galilei',33337),(774920,'strada Vallescuropasso,177 Cigognola (PV) Cigognola, Lombardia 27040 Italy',45.03272,9.25695,'Circolo Vallescuropasso',169016),(774951,'Cernusco sul Naviglio, Lombardia Italy',45.5249795,9.3329595,'Via Cavour, 51',54388),(775039,'Tolmezzo, Friuli Venezia Giulia Italy',46.403496,13.0182123,'Albergo Al Benvenuto',46718),(775042,'Ovaro, Friuli Venezia Giulia 33025 Italy',46.4807459,12.8668439,'Da qualche parte',169588),(775044,'Forni Avoltri, Friuli Venezia Giulia Italy',46.5848845,12.7781655,'irgendwo im Ort',90661),(775046,'Sappada, Veneto Italy',46.5661201,12.6870379,'Da qualche parte',139728),(775047,'Tarvisio, Friuli Venezia Giulia Italy',46.5044339,13.5799589,'Spar',61660),(775085,'Obervintl/Vandois, Trentino Alto Adige / Südtirol Italy',46.7340955,11.2888015,'GH Weisskirche',165536),(775087,'Sexten / Sesto, Trentino Alto Adige / Südtirol Italy',46.7018614,12.3502539,'Skilift',41990),(775116,'Via Floridiani di Hartford, 31 Floridia, Sicilia 96014 Italy',37.0833371,15.1520117,'B&B Bed\'n Design',167130),(775146,'affori Milano, Lombardia Italy',45.5139259,9.1735299,'esselunga',119480),(775183,'Contrada Serrantone, Floridia, Sicilia 96014 Italy',37.0833371,15.1520117,'Liceo Scientifico \"Leonardo Da Vinci\"',167130),(775188,'Povegliano, Veneto Italy',45.7587033,12.2088238,'Parco Ockenheim',152083),(775253,'Torino, Piemonte Italy',45.070312,7.686856499999999,'Parco Naturale La Mandria - Cascina Brero',32962),(775258,'Venaria Reale, Piemonte Italy',45.1301582,7.631047400000001,'Parco Naturale La Mandria - Cascina Brero',68042),(775284,'Via Rossi 1/d Carrara, Toscana 54033 Italy',44.079487,10.098088,'Ancos Confartigiantao Massa Carrara',51797),(775298,'Città di Castello, Umbria 06012 Italy',43.46397830000001,12.2404869,'Calibro',38077),(775302,'via Bonazzi Perugia, Umbria Italy',43.1093295,12.387379,'OCZ Monimbò',35175),(775303,'Camaiore, Toscana Italy',43.9421402,10.2969083,'Via di Mezzo',37843),(775320,'Mirandola, Emilia Romagna Italy',44.88636049999999,11.0632969,'Parco Giochi',58833),(775321,'Mirandola, Emilia Romagna Italy',44.88636049999999,11.0632969,'giardini',58833),(775358,'Fisciano, Campania Italy',40.7731935,14.7965608,'Università degli studi di Salerno',39915),(775360,'Cava de\' Tirreni, Campania Italy',40.6960004,14.7107421,'Cioccolateria',40066),(775361,'Cava de\' Tirreni, Campania Italy',40.6960004,14.7107421,'stazione',40066),(775362,'Cava de\' Tirreni, Campania Italy',40.6960004,14.7107421,'Bar nei pressi del Parco Beethoven',40066),(775447,'Avellino, Campania Italy',40.914388,14.7906121,'Conservatorio',35157),(775470,'via G.B. Tessari 16/d Lavagno, Veneto 37030 Italy',45.42021949999999,11.1370951,'Coop Multi Valori',169731),(775472,'Leonessa, Lazio Italy',42.5660721,12.962445,'Leonessa scorsarella',169734),(775477,'Selvazzano Dentro, Veneto Italy',45.3928629,11.7800578,'Senza Spiga',52473),(775495,'Roma, Lazio Italy',41.9027835,12.4963655,'Istituto M. Massimo',82942),(775696,'Calosso, Piemonte Italy',44.73894809999999,8.228479799999999,'Cascina Castagna',169806),(775732,'via sieghel Cavalese, Trentino Alto Adige / Südtirol Italy',46.2902317,11.4626577,'panificio Betta',80467),(775734,'P.zza squarzanti 23 Ferrara, Emilia Romagna Italy',44.8206581,11.6311027,'BAR CHANCE - FERRARA',38803),(775754,'Via Nolana Pompei, Campania Italy',40.75976379999999,14.4959449,'B&B Certe Notti',44389),(775800,'Via Giacomo Scotti Motta Costigliole d\'Asti, Piemonte 14050 Italy',44.80687,8.148489999999999,'Motta - Via Giacomo Scotti',169843),(775847,'Rosolina, Veneto Italy',45.0761113,12.2421766,'vicino alla spiaggia',144744),(775853,'Isola del Cantone, Liguria Italy',44.64536409999999,8.9566003,'Bus station',169863),(775856,'Cremona, Lombardia Italy',45.133249,10.0226511,'Cafe Fiume',37211),(775865,'San Lorenzo, Liguria Italy',43.8071565,7.569075499999999,'Somewhere',119630),(775867,'Bobbio, Emilia Romagna Italy',44.7700875,9.3859502,'Da qualche parte',118141),(775869,'Sanremo, Liguria Italy',43.81596709999999,7.7760567,'Bahnhofsgelände',37627),(775875,'Bogliaco, Lombardia Italy',45.6751114,10.6507664,'Gargagno',165518),(775876,'Varazze, Liguria Italy',44.3594336,8.577312599999999,'Ortsbereich',51245),(775877,'Varazze, Liguria Italy',44.3594336,8.577312599999999,'Hotel ZAruga',51245),(775878,'Alassio, Liguria Italy',44.014336,8.1811741,'Promenade',63814),(775915,'via Linneo Genova, Liguria 16159 Italy',44.4477546,8.9076522,'Farmacia Linneo',32759),(775938,'solbiate comasco, Lombardia Italy',45.78890519999999,8.933584999999999,'Biblioteca',154709),(775989,'Via dei Glicini 46 A/B Roma, Lazio 00172 Italy',41.8799371,12.5627521,'Enoteca - Wine Not',82942),(775995,'Catanzaro, Italia Catanzaro, Calabria 88100 Italy',38.9291084,16.5856748,'Parco commerciale Le Fontane',103095),(776000,'Bordighera, Liguria Italy',43.7806979,7.6722799,'Da qualche parte',81194),(776070,'Trieste, Friuli Venezia Giulia 34100 Italy',45.6499085,13.7672548,'Polo Zooantropologico',34194),(776080,'via Matteotti, 26/1 Lastra a Signa, Toscana 50055 Italy',43.767546,11.107956,'Emm&mmE Informatica',46062),(776091,'-- da qualche parte in regione (somewhere in the r, Liguria Italy',44.3167917,8.3964938,'Perino',96410),(776128,'Milano, Lombardia Italy',45.4654219,9.1859243,'esselunga affori',119480),(776136,'Cles, Trentino Alto Adige / Südtirol Italy',46.3645084,11.0355046,'biblioteca di cles',150146),(776183,'piazza Risorgimento 1 Stazione di Villafranca Villafranca di Verona, Veneto 37069 Italy',45.35525459999999,10.8474267,'BINARIO ZERO',152839),(776238,'Piazza Aurelio Celio Sabino 50 Roma, Lazio 00174 Italy',41.8530899,12.5583072,'CSPolicarpoOBCZ',82942),(776260,'Desenzano del Garda, Lombardia Italy',45.4714531,10.5333351,'Area cani Parco del laghetto',34425),(776295,'via divisione coduri 92 Cogorno, Liguria 16030 Italy',44.32668,9.3515149,'Nuovo Batesto',146880),(776339,'Trieste, Friuli Venezia Giulia Italy',45.6495264,13.7768182,'DISTRETTO SANITARIO 2 SAN GIACOMO',34194),(776344,'via torre 25 Celle Ligure, Liguria 17015 Italy',44.344895,8.544678000000001,'Hotel Adriana Logis',81033),(776386,'via ghidina 33 Sirmione, Lombardia 25019 Italy',45.4575476,10.6229149,'B&B RAGGIO DI SOLE',91890),(776405,'Trieste, Friuli Venezia Giulia Italy',45.6495264,13.7768182,'DIPARTIMENTO DI PREVENZIONE CORRIDOIO ALIMENTI POSTAZIONE 3',34194),(776434,'Via G. Garibaldi, 7 Gambassi Terme, Toscana 50050 Italy',43.53895000000001,10.9535199,'Biblioteca comunale Gambassi Terme',98415),(776505,'Via Nizza 150, Torino Torino, Piemonte 10126 Italy',45.03991449999999,7.669934499999998,'BiblioCralTo',32962);
/*!40000 ALTER TABLE `Place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Region`
--

DROP TABLE IF EXISTS `Region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Region` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_country` (`country_id`),
  CONSTRAINT `FK_country` FOREIGN KEY (`country_id`) REFERENCES `Country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Region`
--

LOCK TABLES `Region` WRITE;
/*!40000 ALTER TABLE `Region` DISABLE KEYS */;
INSERT INTO `Region` VALUES (503,'Veneto',19),(512,'Emilia Romagna',19),(533,'Lombardia',19),(540,'Lazio',19),(541,'Calabria',19),(620,'Liguria',19),(646,'Abruzzo',19),(675,'Campania',19),(692,'Piemonte',19),(731,'Friuli Venezia Giulia',19),(732,'Marche',19),(815,'Toscana',19),(882,'Basilicata',19),(894,'Sicilia',19),(1211,'Umbria',19),(1369,'Molise',19),(1637,'Sardegna',19),(1867,'Trentino Alto Adige / Südtirol',19),(13130,'-- wild released somewhere in Italy --',19);
/*!40000 ALTER TABLE `Region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id_user` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `release_places`
--

DROP TABLE IF EXISTS `release_places`;
/*!50001 DROP VIEW IF EXISTS `release_places`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `release_places` AS SELECT 
 1 AS `country`,
 1 AS `region`,
 1 AS `city`,
 1 AS `place`,
 1 AS `lat`,
 1 AS `lng`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `release_places`
--

/*!50001 DROP VIEW IF EXISTS `release_places`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `release_places` AS select `cn`.`name` AS `country`,`r`.`name` AS `region`,`ct`.`name` AS `city`,`p`.`name` AS `place`,`p`.`lat` AS `lat`,`p`.`lng` AS `lng` from (((`Country` `cn` join `Region` `r`) join `City` `ct`) join `Place` `p`) where ((`r`.`country_id` = `cn`.`id`) and (`ct`.`region_id` = `r`.`id`) and (`p`.`city_id` = `ct`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-30 23:41:14
