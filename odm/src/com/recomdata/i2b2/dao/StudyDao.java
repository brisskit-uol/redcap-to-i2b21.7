/**
 * Copyright(c)  2011-2012 Recombinant Data Corp., All rights Reserved
 */

package com.recomdata.i2b2.dao;

import java.util.Calendar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.recomdata.i2b2.entity.I2B2StudyInfo;

/**
 * StudyDao.java the implementation for IStudyDao
 * 
 * DAO used to turn ODM Study definitions data into i2b2 records. author: Alex
 * Wu on 09/02/2011
 */
public class StudyDao implements IStudyDao {
	private PreparedStatement insertMetadataStatement = null;
	private int metadataBatchCount = 100;
	
	final static Logger logger = LogManager.getLogger(StudyDao.class);
	
	
	public StudyDao() throws SQLException {
		Connection con = I2B2DBUtils.getI2B2DBConnection();
		insertMetadataStatement = con.prepareStatement(IStudyDao.INSERT_SQL);
	}

	/**
	 * Method to insert ODM data into metadata.STUDY
	 */
	public void insertMetadata(I2B2StudyInfo studyInfo) throws SQLException {	
		
		logger.info("StudyDao insertMetadata " + studyInfo);
		
		insertMetadataStatement.setInt(1, studyInfo.getChlevel());
		insertMetadataStatement.setString(2, studyInfo.getCfullname());
		insertMetadataStatement.setString(3, studyInfo.getCname());
		insertMetadataStatement.setString(4, studyInfo.getCsynonmCd());
		insertMetadataStatement.setString(5, studyInfo.getCvisualAttributes());
		insertMetadataStatement.setInt(6, studyInfo.getCtotalNum());
		insertMetadataStatement.setString(7, studyInfo.getCbasecode());
		insertMetadataStatement.setString(8, studyInfo.getCmetadataxml());
		insertMetadataStatement.setString(9, studyInfo.getCfactTableColumn());
		insertMetadataStatement.setString(10, studyInfo.getCtablename());
		insertMetadataStatement.setString(11, studyInfo.getCcolumnname());
		insertMetadataStatement.setString(12, studyInfo.getCcolumnDatatype());
		insertMetadataStatement.setString(13, studyInfo.getCoperator());
		insertMetadataStatement.setString(14, studyInfo.getCdimcode());
		insertMetadataStatement.setString(15, studyInfo.getCcomment());
		insertMetadataStatement.setString(16, studyInfo.getCtooltip());
		insertMetadataStatement.setString(17, studyInfo.getMappliedPath());
		insertMetadataStatement.setDate(18, I2B2DBUtils.getSQLDateFromUtilDate(studyInfo.getUpdateDate()));
		insertMetadataStatement.setDate(19, I2B2DBUtils.getSQLDateFromUtilDate(studyInfo.getDownloadDate()));
		insertMetadataStatement.setDate(20, I2B2DBUtils.getSQLDateFromUtilDate(studyInfo.getImportDate()));
		//insertMetadataStatement.setString(21, studyInfo.getSourceSystemCd());
		insertMetadataStatement.setString(21, "redcap");	
		insertMetadataStatement.setString(22, studyInfo.getValuetype());

		if (Boolean.getBoolean("batch.disabled")) { 
			insertMetadataStatement.execute();
		} else {
			insertMetadataStatement.addBatch();
			
			if (++metadataBatchCount >= BATCH_SIZE) {
				executeBatch();			
			}
		}
	}
	
	public void executeBatch() throws SQLException {
		logger.info("StudyDao executeBatch ");
		
		insertMetadataStatement.executeBatch();
		metadataBatchCount = 0;
	}

	/**
	 * method to delete records in metadata.STUDY and insert level 0 record
	 */
	public void preSetupI2B2Study(String projectID, String sourceSystem) throws SQLException {
		
		logger.info("StudyDao preSetupI2B2Study projectID " + projectID + " sourceSystem " + sourceSystem);
		
		
		Date currentDate = I2B2DBUtils.getSQLDateFromUtilDate(Calendar.getInstance().getTime());

		logger.info("StudyDao preSetupI2B2Study " + currentDate);
		
		deleteI2B2Study(projectID, sourceSystem);

		if (!checkI2B2Data(0, "\\STUDY\\")) {
			insertMetadataStatement.setInt(1, 0);
			insertMetadataStatement.setString(2, "\\STUDY\\");
			insertMetadataStatement.setString(3, "Study");
			insertMetadataStatement.setString(4, "N");
			insertMetadataStatement.setString(5, "FA");
			insertMetadataStatement.setInt(6, 0);
			insertMetadataStatement.setString(7, null);
			insertMetadataStatement.setString(8, null);
			insertMetadataStatement.setString(9, "concept_cd");
			insertMetadataStatement.setString(10, "concept_dimension");
			insertMetadataStatement.setString(11, "concept_path");
			insertMetadataStatement.setString(12, "T");
			insertMetadataStatement.setString(13, "LIKE");
			insertMetadataStatement.setString(14, "\\STUDY\\");
			insertMetadataStatement.setString(15, null);
			insertMetadataStatement.setString(16, "STUDY");
			insertMetadataStatement.setString(17, "@");
			insertMetadataStatement.setDate(18, currentDate);
			insertMetadataStatement.setDate(19, currentDate);
			insertMetadataStatement.setDate(20, currentDate);
			insertMetadataStatement.setString(21, null);
			insertMetadataStatement.setString(22, null);
			insertMetadataStatement.executeUpdate();			
		}
	}

	/**
	 * check to see if i2b2 record already existed
	 * @throws SQLException 
	 */
	private boolean checkI2B2Data(int c_hlevel, String c_fullNamePath) throws SQLException {
		int count = 0;

		logger.info("StudyDao checkI2B2Data c_hlevel " + c_hlevel + " c_fullNamePath " + c_fullNamePath);
		
		Statement st = I2B2DBUtils.getI2B2DBConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT count(*) FROM STUDY WHERE C_HLEVEL = "
						+ c_hlevel + " AND C_FULLNAME = " + "'" + c_fullNamePath + "'");

		if (rs.next()) {
			count = rs.getInt(1);
		}
		
		return count > 0;
	}
	
	/**
	 * Delete all records in testing study table. cautiously for conducting this
	 * in production
	 */
	private void deleteI2B2Study(String projectID, String sourceSystem) throws SQLException {
		
		String cPath = "\\\\STUDY\\\\" + sourceSystem + ":" + projectID + "\\\\%";
		
		logger.info("StudyDao deleteI2B2Study cPath " + cPath);
		
		
		Statement stmt = I2B2DBUtils.getI2B2DBConnection().createStatement();
		
		logger.info("StudyDao deleteI2B2Study stmt " + stmt.toString());
		
		String deleteStudyOntologySql = "DELETE FROM STUDY WHERE C_FULLNAME LIKE '" + cPath + "'";
		
		logger.info("StudyDao deleteI2B2Study deleteStudyOntologySql " + deleteStudyOntologySql);
		
		stmt.executeUpdate(deleteStudyOntologySql);
		
		logger.info("StudyDao deleteI2B2Study after execute ");
		
		stmt.close();
	}
}