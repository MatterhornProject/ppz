INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('48','Poznań-Wilda','30');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('49','Poznań-Winogrady','30');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('50','Piekary Śląskie','24');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('51','Aleksandrów Kujawski','04');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('52','Augustów','20');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('53','Bartoszyce','28');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('54','Będzin','24');
INSERT INTO `City`(`id`, `name`, `voivodeship_id`) VALUES ('306','Wieluń','10');

INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('30','Wielkopolskie');
INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('24','Śląskie');
INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('20','Podlaskie');
INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('28','Warmińsko-Mazurskie');
INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('04','Kujawsko-Pomorskie');
INSERT INTO `Voivodeship`(`id`, `name`) VALUES ('10','Łódzkie');

INSERT INTO `TaxOffice`(`id`, `name`, `city_id`, `voivodeship_id`, `details_id`) VALUES ('783','Urząd Skarbowy Poznań-Wilda','48','30','3026');
INSERT INTO `TaxOffice`(`id`, `name`, `city_id`, `voivodeship_id`, `details_id`) VALUES ('979', 'Urząd Skarbowy Poznań-Winogrady', '49', '30', '3025');
INSERT INTO `TaxOffice`(`id`, `name`, `city_id`, `voivodeship_id`, `details_id`) VALUES ('832', 'Urząd Skarbowy w Wieluniu', '306', '10', '1024');

INSERT INTO `TaxOffice_Details` (`id`, `street`, `zipcode`, `city`, `phone`, `fax`, `email`, `www`) VALUES
('3026', 'Dolna Wilda 80B', '61-501', 'Poznań', '(61) 832-88-00', '(61) 833-38-12', 'us.poznan-wilda@mf.gov.pl', 'www.wielkopolskie.kas.gov.pl/urzad-skarbowy-poznan-wilda');

INSERT INTO `TaxOffice_Details` (`id`, `street`, `zipcode`, `city`, `phone`, `fax`, `email`, `www`) VALUES
('3025', 'Zygmunta Wojciechowskiego  3/5', '60-685', 'Poznań', '(61) 827-01-00', '(61) 827-01-01', 'us.poznan-winogrady@mf.gov.pl', 'www.wielkopolskie.kas.gov.pl/urzad-skarbowy-poznan-winogrady');

INSERT INTO `TaxOffice_Details` (`id`, `street`, `zipcode`, `city`, `phone`, `fax`, `email`, `www`) VALUES
('1024' , 'Krakowskie Przedmieście  34', '98-300', 'Wieluń', '(43) 843-91-36', '(43) 843-91-59', 'us.wielun@mf.gov.pl', 'www.lodzkie.kas.gov.pl/urzad-skarbowy-w-wieluniu');