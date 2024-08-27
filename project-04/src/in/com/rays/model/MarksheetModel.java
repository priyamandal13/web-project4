package in.com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import in.com.rays.bean.MarksheetBean;
import in.com.rays.exception.DuplicateRecordException;
import in.com.rays.util.JDBCDataSource;

public class MarksheetModel {
	public Integer nextPk() throws Exception {
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_marksheet");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = (int) rs.getLong(1);
		}
		return pk + 1;

	}

	public void add(MarksheetBean bean) throws Exception {
		MarksheetBean existBean = findByRollNo(bean.getRollNo());

		if (existBean != null) {
			throw new DuplicateRecordException("RollNo Already Exist...!");
		}

		int pk = nextPk();
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getRollNo());
		pstmt.setLong(3, bean.getStudentId());
		pstmt.setString(4, bean.getName());
		pstmt.setInt(5, bean.getPhysics());
		pstmt.setInt(6, bean.getChemistry());
		pstmt.setInt(7, bean.getMaths());
		pstmt.setString(8, bean.getCreatedBy());
		pstmt.setString(9, bean.getModifiedBy());
		pstmt.setTimestamp(10, bean.getCreatedDatetime());
		pstmt.setTimestamp(11, bean.getModifiedDatetime());
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println(" Data Added==>" + i);

	}

	public void update(MarksheetBean bean) throws Exception {
		MarksheetBean existBean = findByRollNo(bean.getRollNo());

		if (existBean != null && existBean.getRollNo() != bean.getRollNo()) {
			throw new DuplicateRecordException("RollNo Already Exist");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_marksheet set roll_no=?, student_id=?, name=?, physics=?, chemistry=?, maths=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");
		pstmt.setString(1, bean.getRollNo());
		pstmt.setLong(2, bean.getStudentId());
		pstmt.setString(3, bean.getName());
		pstmt.setInt(4, bean.getPhysics());
		pstmt.setInt(5, bean.getChemistry());
		pstmt.setInt(6, bean.getMaths());
		pstmt.setString(7, bean.getCreatedBy());
		pstmt.setString(8, bean.getModifiedBy());
		pstmt.setTimestamp(9, bean.getCreatedDatetime());
		pstmt.setTimestamp(10, bean.getModifiedDatetime());
		pstmt.setLong(11, bean.getId());
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println(" Data Updated==>" + i);

	}

	public void delete(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete  from st_marksheet where id=?");
		pstmt.setLong(1, id);
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println(" Data Deleted==>" + i);

	}

	public MarksheetBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		MarksheetBean bean = null;
		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
		}
		return bean;

	}

	public MarksheetBean findByRollNo(String roll_no) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where roll_no=?");
		pstmt.setString(1, roll_no);
		ResultSet rs = pstmt.executeQuery();
		MarksheetBean bean = null;
		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
		}
		return bean;

	}

	public List search(MarksheetBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_marksheet where 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id= " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append("  limit " + pageNo + "," + pageSize);
		}
		System.out.println("sql=>" + sql);
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		List list = new ArrayList();
		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
			list.add(bean);
		}

		return list;

	}
}