CREATE TABLE `NewTable` (
                            `ID`  int(11) NOT NULL AUTO_INCREMENT ,
                            `ACCOUNT_ID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                            `NAME`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                            `TOKEN`  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                            `GMT_CREATE`  bigint(20) NULL DEFAULT NULL ,
                            `GMT_MODIFIED`  bigint(20) NULL DEFAULT NULL ,
                            `bio`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                            PRIMARY KEY (`ID`)
)
    DEFAULT CHARACTER SET=utf8
    COLLATE=utf8_general_ci
    ROW_FORMAT=DYNAMIC
;