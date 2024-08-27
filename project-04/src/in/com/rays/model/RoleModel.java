package in.com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.bean.CollegeBean;
import in.com.rays.bean.RoleBean;
import in.com.rays.exception.DuplicateRecordException;
import in.com.rays.util.JDBCDataSource;

public class RoleModel {
	public long pk() throws Exception {
		long pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		return pk + 1;

	}

	public void add(RoleBean bean) throws DuplicateRecordException, Exception {
		int pk = (int) pk();

		RoleBean existBean = findByName(bean.getName());
		if (existBean != null) {
			throw new DuplicateRecordException("RoleName Already Exist");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?, ?, ?, ?, ?, ?, ?)");
		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getDescription());
		pstmt.setString(4, bean.getCreatedBy());
		pstmt.setString(5, bean.getModifiedBy());
		pstmt.setTimestamp(6, bean.getCreatedDatetime());
		pstmt.setTimestamp(7, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("Data Added=>" + i);

	}

	public void update(RoleBean bean) throws Exception {

		RoleBean existBean = findByName(bean.getName());
		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("RoleName Already Exist");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_role set name=?, description=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getDescription());
		pstmt.setString(3, bean.getCreatedBy());
		pstmt.setString(4, bean.getModifiedBy());
		pstmt.setTimestamp(5, bean.getCreatedDatetime());
		pstmt.setTimestamp(6, bean.getModifiedDatetime());
		pstmt.setLong(7, bean.getId());
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("Data Updated=>" + i);

	}

	public void delete(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id=?");
		pstmt.setLong(1, id);
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("Data Deleted=>" + i);
	}

	public RoleBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		RoleBean bean = null;
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public RoleBean findByName(String name) throws SQLException {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name = ?");
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		RoleBean bean = null;
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;

	}

	public List search(RoleBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_role where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " ," + pageSize);
		}
		System.out.println("sql=>" + sql);

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		List list = new ArrayList();
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));
			list.add(bean);
		}

		return list;

	}
}