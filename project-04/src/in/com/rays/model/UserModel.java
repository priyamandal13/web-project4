package in.com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.bean.RoleBean;
import in.com.rays.bean.UserBean;
import in.com.rays.exception.DuplicateRecordException;
import in.com.rays.util.JDBCDataSource;

public class UserModel {

	public long pk() throws Exception {
		long pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select max(id) from st_user");
		ResultSet r = stmt.executeQuery();
		while (r.next()) {
			pk = r.getLong(1);
		}
		return pk + 1;
	}

	public void add(UserBean bean) throws Exception {
		int pk = (int) pk();

		UserBean existBean = findByLogin(bean.getLoginid());
		if (existBean != null) {
			throw new DuplicateRecordException("LoginID Already Exist...");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setString(4, bean.getLoginid());
		pstmt.setString(5, bean.getPassword());
		pstmt.setString(6, bean.getConfirmPassword());
		pstmt.setDate(7, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(8, bean.getMobileNo());
		pstmt.setLong(9, bean.getRoleld());
		pstmt.setString(10, bean.getGender());
		pstmt.setString(11, bean.getCreatedBy());
		pstmt.setString(12, bean.getModifiedBy());
		pstmt.setTimestamp(13, bean.getCreatedDatetime());
		pstmt.setTimestamp(14, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("User Added=>" + i);

	}

	public void update(UserBean bean) throws Exception {

		UserBean existBean = findByLogin(bean.getLoginid());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("LoginID Already Exist...");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_user set first_name=?, last_name=?, login=?, password=?, confirm_password=?, dob=?, mobile_no=?, role_id=?, gender=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");
		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLoginid());
		pstmt.setString(4, bean.getPassword());
		pstmt.setString(5, bean.getConfirmPassword());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(7, bean.getMobileNo());
		pstmt.setLong(8, bean.getRoleld());
		pstmt.setString(9, bean.getGender());
		pstmt.setString(10, bean.getCreatedBy());
		pstmt.setString(11, bean.getModifiedBy());
		pstmt.setTimestamp(12, bean.getCreatedDatetime());
		pstmt.setTimestamp(13, bean.getModifiedDatetime());
		pstmt.setLong(14, bean.getId());

		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("User Updated=>" + i);

	}

	public void delete(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id=?");
		pstmt.setLong(1, id);
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("User Delete=>" + i);

	}

	public UserBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginid(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setConfirmPassword(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setMobileNo(rs.getString(8));
			bean.setRoleld(rs.getLong(9));
			bean.setGender(rs.getString(10));
			bean.setCreatedBy(rs.getString(11));
			bean.setModifiedBy(rs.getString(12));
			bean.setCreatedDatetime(rs.getTimestamp(13));
			bean.setModifiedDatetime(rs.getTimestamp(14));
		}
		return bean;

	}

	public UserBean authenticate(String login, String password) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login=? and password=?");
		pstmt.setString(1, login);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginid(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setConfirmPassword(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setMobileNo(rs.getString(8));
			bean.setRoleld(rs.getLong(9));
			bean.setGender(rs.getString(10));
			bean.setCreatedBy(rs.getString(11));
			bean.setModifiedBy(rs.getString(12));
			bean.setCreatedDatetime(rs.getTimestamp(13));
			bean.setModifiedDatetime(rs.getTimestamp(14));
		}
		return bean;

	}

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append("and id=" + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append("and first_name like'" + bean.getFirstName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append("and dob like'" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append("limit" + pageNo + "," + pageSize);
		}
		System.out.println("sql=>" + sql);

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		List list = new ArrayList();

		while (rs.next()) {
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginid(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setConfirmPassword(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setMobileNo(rs.getString(8));
			bean.setRoleld(rs.getLong(9));
			bean.setGender(rs.getString(10));
			bean.setCreatedBy(rs.getString(11));
			bean.setModifiedBy(rs.getString(12));
			bean.setCreatedDatetime(rs.getTimestamp(13));
			bean.setModifiedDatetime(rs.getTimestamp(14));
			list.add(bean);
		}
		return list;
	}

	public UserBean findByLogin(String login) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login=?");
		pstmt.setString(1, login);
		ResultSet rs = pstmt.executeQuery();
		UserBean bean = null;
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginid(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setConfirmPassword(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setMobileNo(rs.getString(8));
			bean.setRoleld(rs.getLong(9));
			bean.setGender(rs.getString(10));
			bean.setCreatedBy(rs.getString(11));
			bean.setModifiedBy(rs.getString(12));
			bean.setCreatedDatetime(rs.getTimestamp(13));
			bean.setModifiedDatetime(rs.getTimestamp(14));
		}
		return bean;

	}
}