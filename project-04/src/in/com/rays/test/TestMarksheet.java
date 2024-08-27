package in.com.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.com.rays.bean.MarksheetBean;
import in.com.rays.model.MarksheetModel;

public class TestMarksheet {
	public static void main(String[] args) throws Exception {

		testAdd();
//		testUpdate();
//		 testDelete();
//		 testSearch():
	}

	public static void testAdd() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("1001");
		bean.setStudentId(1);
		bean.setName("Virat");
		bean.setPhysics(2);
		bean.setChemistry(3);
		bean.setMaths(4);
		bean.setCreatedBy("Self");
		bean.setModifiedBy("Self");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		MarksheetModel model = new MarksheetModel();
		model.add(bean);
	}

	public static void testUpdate() throws Exception {
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("");
		bean.setStudentId(1);
		bean.setName("");
		bean.setPhysics(2);
		bean.setChemistry(3);
		bean.setMaths(4);
		bean.setCreatedBy("Self");
		bean.setModifiedBy("Self");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		MarksheetModel model = new MarksheetModel();
		model.update(bean);
	}

	public static void testDelete() throws Exception {
		MarksheetModel model = new MarksheetModel();
		model.delete(2);
	}

	public static void testSearch() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean.setId(1);
		// bean.setFirstName("m");

		MarksheetModel model = new MarksheetModel();
		List list = model.search(bean, 1, 2);
		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (MarksheetBean) it.next();

			System.out.print(bean.getId());
			System.out.print(" " + bean.getRollNo());
			System.out.print(" " + bean.getStudentId());
			System.out.print(" " + bean.getName());
			System.out.print(" " + bean.getPhysics());
			System.out.print(" " + bean.getChemistry());
			System.out.print(" " + bean.getMaths());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());

		}

	}
}