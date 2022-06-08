CREATE TABLE `City` (
  `id` int NOT NULL,
  `name` varchar(60) NOT NULL,
  `voivodeship_id` int NOT NULL
);


CREATE TABLE `TaxOffice` (
  `id` int  NOT NULL,
  `name` varchar(85) NOT NULL,
  `city_id` int NOT NULL,
  `voivodeship_id` int NOT NULL,
  `details_id` int  NOT NULL
);


CREATE TABLE `TaxOffice_Details` (
  `id` int  NOT NULL,
  `street` varchar(100) NOT NULL,
  `zipcode` varchar(6) NOT NULL,
  `city` varchar(80) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `fax` varchar(20) NOT NULL,
  `email` varchar(70) NOT NULL,
  `www` varchar(150) NOT NULL
);


CREATE TABLE `Voivodeship` (
  `id` int NOT NULL,
  `name` varchar(65) NOT NULL
);

