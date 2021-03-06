
--------------------------------------------------------
--  Create Table STUDY
--------------------------------------------------------
CREATE TABLE STUDY
   (  C_HLEVEL INT      NOT NULL, 
  C_FULLNAME VARCHAR(700) NOT NULL, 
  C_NAME VARCHAR(2000)    NOT NULL, 
  C_SYNONYM_CD CHAR(1)    NOT NULL, 
  C_VISUALATTRIBUTES CHAR(3)  NOT NULL, 
  C_TOTALNUM INT      NULL, 
  C_BASECODE VARCHAR(50)  NULL, 
  C_METADATAXML TEXT    NULL, 
  C_FACTTABLECOLUMN VARCHAR(50) NOT NULL, 
  C_TABLENAME VARCHAR(50) NOT NULL, 
  C_COLUMNNAME VARCHAR(50)  NOT NULL, 
  C_COLUMNDATATYPE VARCHAR(50)  NOT NULL, 
  C_OPERATOR VARCHAR(10)  NOT NULL, 
  C_DIMCODE VARCHAR(700)  NOT NULL, 
  C_COMMENT TEXT      NULL, 
  C_TOOLTIP VARCHAR(900)  NULL,
  M_APPLIED_PATH VARCHAR(700) NULL, 
  UPDATE_DATE TIMESTAMP    NOT NULL, 
  DOWNLOAD_DATE TIMESTAMP  NULL, 
  IMPORT_DATE TIMESTAMP  NULL, 
  SOURCESYSTEM_CD VARCHAR(50) NULL, 
  VALUETYPE_CD VARCHAR(50)  NULL,
  M_EXCLUSION_CD  VARCHAR(25) NULL,
  C_PATH  VARCHAR(700)   NULL,
  C_SYMBOL  VARCHAR(50) NULL
   ) ;
   
GO

/* create concept_dimension table with clustered PK on concept_path */
CREATE TABLE concept_dimension ( 
	concept_path   	varchar(700) NOT NULL,
	concept_cd     	varchar(50) NULL,
	name_char      	varchar(2000) NULL,
	concept_blob   	text NULL,
	update_date    	timestamp NULL,
	download_date  	timestamp NULL,
	import_date    	timestamp NULL,
	sourcesystem_cd	varchar(50) NULL,
      UPLOAD_ID       INT NULL,
    CONSTRAINT CONCEPT_DIMENSION_PK PRIMARY KEY(concept_path)
	);
CREATE INDEX CD_IDX_UPLOADID ON CONCEPT_DIMENSION(UPLOAD_ID)
;

/* create observation_fact table with NONclustered PK on encounter_num,concept_cd,provider_id,start_date,modifier_cd  */
CREATE TABLE OBSERVATION_FACT ( 
	ENCOUNTER_NUM  	INT NOT NULL,
	REDCAP_SUBJECT_ID VARCHAR(50),
	PATIENT_NUM    	INT,
	CONCEPT_CD     	VARCHAR(50) NOT NULL,
	PROVIDER_ID    	VARCHAR(50) NOT NULL,
	START_DATE     	TIMESTAMP NOT NULL,
	MODIFIER_CD    	VARCHAR(100) NOT NULL,
	VALTYPE_CD     	VARCHAR(50) NULL,
	TVAL_CHAR      	VARCHAR(255) NULL,
	NVAL_NUM       	DECIMAL(18,5) NULL,
	INSTANCE_NUM	INT NULL,
	VALUEFLAG_CD   	VARCHAR(50) NULL,
	QUANTITY_NUM   	DECIMAL(18,5) NULL,
	UNITS_CD       	VARCHAR(50) NULL,
	END_DATE       	TIMESTAMP NULL,
	LOCATION_CD    	VARCHAR(50) NULL,
	OBSERVATION_BLOB TEXT NULL,
	CONFIDENCE_NUM 	DECIMAL(18,5) NULL,
	UPDATE_DATE    	TIMESTAMP NULL,
	DOWNLOAD_DATE  	TIMESTAMP NULL,
	IMPORT_DATE    	TIMESTAMP NULL,
	SOURCESYSTEM_CD	VARCHAR(50) NULL, 
    UPLOAD_ID         	INT NULL,
    CONSTRAINT OBSERVATION_FACT_PK PRIMARY KEY nonclustered (ENCOUNTER_NUM,CONCEPT_CD,PROVIDER_ID,START_DATE,MODIFIER_CD)
	)
;

DROP TABLE project2.observation_fact;

CREATE TABLE project2.observation_fact
(
  ENCOUNTER_NUM INTEGER NOT NULL,
  REDCAP_SUBJECT_ID CHARACTER VARYING(50) NOT NULL,
  PATIENT_NUM INTEGER,
  CONCEPT_CD CHARACTER VARYING(50) NOT NULL,
  PROVIDER_ID CHARACTER VARYING(50) NOT NULL,
  START_DATE TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  MODIFIER_CD CHARACTER VARYING(100) NOT NULL DEFAULT '@'::CHARACTER VARYING,
  INSTANCE_NUM INTEGER NOT NULL DEFAULT 1,
  VALTYPE_CD CHARACTER VARYING(50),
  TVAL_CHAR CHARACTER VARYING(255),
  NVAL_NUM NUMERIC(18,5),
  VALUEFLAG_CD CHARACTER VARYING(50),
  QUANTITY_NUM NUMERIC(18,5),
  UNITS_CD CHARACTER VARYING(50),
  END_DATE TIMESTAMP WITHOUT TIME ZONE,
  LOCATION_CD CHARACTER VARYING(50),
  OBSERVATION_BLOB TEXT,
  CONFIDENCE_NUM NUMERIC(18,5),
  UPDATE_DATE TIMESTAMP WITHOUT TIME ZONE,
  DOWNLOAD_DATE TIMESTAMP WITHOUT TIME ZONE,
  IMPORT_DATE TIMESTAMP WITHOUT TIME ZONE,
  SOURCESYSTEM_CD CHARACTER VARYING(50),
  UPLOAD_ID INTEGER,
  TEXT_SEARCH_INDEX SERIAL NOT NULL,
  CONSTRAINT OBSERVATION_FACT_PK PRIMARY KEY (CONCEPT_CD, MODIFIER_CD, START_DATE, ENCOUNTER_NUM, INSTANCE_NUM, PROVIDER_ID)
)
;

CREATE INDEX OF_IDX_ALLObservation_Fact ON project2.OBSERVATION_FACT
(
	PATIENT_NUM ,
	ENCOUNTER_NUM ,
	CONCEPT_CD ,
	START_DATE ,
	PROVIDER_ID ,
	MODIFIER_CD ,
	INSTANCE_NUM,
	VALTYPE_CD ,
	TVAL_CHAR ,
	NVAL_NUM ,
	VALUEFLAG_CD ,
	QUANTITY_NUM ,
	UNITS_CD ,
	END_DATE ,
	LOCATION_CD ,
	CONFIDENCE_NUM
)
;

CREATE INDEX OF_IDX_Start_Date ON project2.OBSERVATION_FACT(START_DATE, PATIENT_NUM)
;
CREATE INDEX OF_IDX_Modifier ON project2.OBSERVATION_FACT(MODIFIER_CD)
;
CREATE INDEX OF_IDX_Encounter_Patient ON project2.OBSERVATION_FACT(ENCOUNTER_NUM, PATIENT_NUM, INSTANCE_NUM)
;
CREATE INDEX OF_IDX_UPLOADID ON project2.OBSERVATION_FACT(UPLOAD_ID)
;
CREATE INDEX OF_IDX_SOURCESYSTEM_CD ON project2.OBSERVATION_FACT(SOURCESYSTEM_CD)
;
CREATE UNIQUE INDEX OF_TEXT_SEARCH_UNIQUE ON project2.OBSERVATION_FACT(TEXT_SEARCH_INDEX)
;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA project2 TO project2;
GRANT ALL PRIVILEGES ON SCHEMA project2 TO project2 ;
GRANT ALL PRIVILEGES ON ALL sequences IN SCHEMA project2 TO project2
