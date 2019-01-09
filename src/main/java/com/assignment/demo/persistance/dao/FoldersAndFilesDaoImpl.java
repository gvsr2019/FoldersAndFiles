/**
 * 
 */
package com.assignment.demo.persistance.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.assignment.demo.persistance.dto.FnFDTO;

/**
 * @author gsiva
 *
 */

@Repository
public class FoldersAndFilesDaoImpl implements FoldersAndFilesDAO {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int[] insertFnfs(List<FnFDTO> fnfList) throws Exception {
		int[] result;
		result = jdbcTemplate.batchUpdate(INSERT_QRY, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				FnFDTO fnF = fnfList.get(i);
				ps.setString(1, fnF.getLocation());
				ps.setString(2, fnF.getName());
				ps.setString(3, String.valueOf(fnF.getCategory()));
			}

			@Override
			public int getBatchSize() {
				return fnfList.size();
			}
		});
		logger.info("Inserted records successfully");
		return result;
	}

	@Override
	public List<FnFDTO> getFnfs(String location) throws Exception {
		List<FnFDTO> result;
		result = jdbcTemplate.query(SELECT_QRY, new Object[] { location }, (RowMapper<FnFDTO>) (rs, rowNum) -> {
			return new FnFDTO(rs.getString("NAME"), rs.getString("CATG").charAt(0));
		});
		logger.info("Executed select query for getting records succesfully");
		return result;
	}

	private String INSERT_QRY = "insert into FNF (LOC,NAME,CATG) values (?,?,?)";
	private String SELECT_QRY = "select NAME,CATG from FNF where LOC = ?";

}
