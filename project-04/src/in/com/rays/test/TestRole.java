package in.com.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.com.rays.bean.RoleBean;
import in.com.rays.model.RoleModel;

public class TestRole {
	public static void main(String[] args) throws Exception {
		testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
	}

	public static void testAdd() throws Exception {
		RoleBean bean = new RoleBean();

		bean.setId(3);
		bean.setName("Vinjal");
		bean.setDescription("description");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();
		model.add(bean);
	}

	public static void testUpdate() throws Exception {
		RoleBean bean = new RoleBean();
		bean.setName("priyanka");
		bean.setDescription("description");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(3);

		RoleModel model = new RoleModel();
		model.update(bean);

	}

	public static void testDelete() throws Exception {
		RoleModel model = new RoleModel();
		model.delete(4);
	}

	public static void testSearch() throws Exception {

		RoleBean bean = new RoleBean();
		bean.setId(1);
		// bean.setName("m");

		RoleModel model = new RoleModel();
		List list = model.search(bean, 1, 1);
		Iterator i = list.iterator();
		while (i.hasNext()) {
			bean = (RoleBean) i.next();
			System.out.print(" " + bean.getId());
			System.out.print(" " + bean.getName());
			System.out.print(" " + bean.getDescription());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());

		}
	}
}