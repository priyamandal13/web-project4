package in.com.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.com.rays.bean.StudentBean;
import in.com.rays.model.StudentModel;

public class TestStudent {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		   testUpdate();

	}

	public static void testAdd() throws Exception {

		StudentBean bean = new StudentBean();

		bean.setId(1);
		bean.setFirstName("");
		bean.setLastName("");
		bean.setDob(new Date());
		bean.setGender("");
		bean.setMobileNo("");
		bean.setEmail("");
		bean.setCollegeId(2);
		bean.setCollegeName("");
		bean.setCreatedBy("rajl@gmail.com");
		bean.setModifiedBy("aryan@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();
		model.add(bean);

	}

	public static void testUpdate() throws Exception {

		StudentBean bean = new StudentBean();

		bean.setId(1);
		bean.setFirstName("");
		bean.setLastName("");
		bean.setDob(new Date());
		bean.setGender("");
		bean.setMobileNo("");
		bean.setEmail("");
		bean.setCollegeId(2);
		bean.setCollegeName("");
		bean.setCreatedBy("rajl@gmail.com");
		bean.setModifiedBy("aryan@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();
		model.update(bean);

	}

	public static void testDelete() throws Exception {

		StudentModel model = new StudentModel();
		model.delete(1);
	}

	public static void testFindByPk() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByPk(1);
		if (bean != null) {
			System.out.print(" " + bean.getId());
			System.out.print(" " + bean.getFirstName());
			System.out.print(" " + bean.getLastName());
			System.out.print(" " + bean.getDob());
			System.out.print(" " + bean.getGender());
			System.out.print(" " + bean.getMobileNo());
			System.out.print(" " + bean.getEmail());
			System.out.print(" " + bean.getCollegeId());
			System.out.print(" " + bean.getCollegeName());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());
		} else {
			System.out.println(" id not found ");
		}

	}

	public static void testSearch() throws Exception {

		StudentBean bean = new StudentBean();
		bean.setId(1);
		// bean.setName("m");

		StudentModel model = new StudentModel();
		List list = model.search(bean, 1, 1);
		Iterator i = list.iterator();
		while (i.hasNext()) {
			bean = (StudentBean) i.next();
			System.out.print(" " + bean.getId());
			System.out.print(" " + bean.getFirstName());
			System.out.print(" " + bean.getLastName());
			System.out.print(" " + bean.getDob());
			System.out.print(" " + bean.getGender());
			System.out.print(" " + bean.getMobileNo());
			System.out.print(" " + bean.getEmail());
			System.out.print(" " + bean.getCollegeId());
			System.out.print(" " + bean.getCollegeName());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());

		}
	}
}