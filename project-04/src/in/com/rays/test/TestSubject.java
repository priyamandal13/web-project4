package in.com.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.com.rays.bean.SubjectBean;
import in.com.rays.model.SubjectModel;

public class TestSubject {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		 testSearch();

	}

	public static void testAdd() throws Exception {

		SubjectBean bean = new SubjectBean();
		bean.setId(15);
		bean.setName("suraj");
		bean.setCourseId(3);
		bean.setCourseName("");
		bean.setDescription("");
		bean.setCreatedBy("rajl@gmail.com");
		bean.setModifiedBy("aryan@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		SubjectModel model = new SubjectModel();
		model.add(bean);

	}

	public static void testUpdate() throws Exception {

		SubjectBean bean = new SubjectBean();
		bean.setId(1);
		bean.setName("");
		bean.setCourseId(4);
		bean.setCourseName("");
		bean.setDescription("");
		bean.setCreatedBy("Ssuraj@gmail.com");
		bean.setModifiedBy("suraj@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		SubjectModel model = new SubjectModel();
		model.update(bean);

	}

	public static void testDelete() throws Exception {

		SubjectModel model = new SubjectModel();
		model.delete(1);
	}

	public static void testName() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByName("");

		if (bean != null) {
			System.out.print(" " + bean.getId());
			System.out.println(" " + bean.getName());
			System.out.println(" " + bean.getCourseId());
			System.out.println(" " + bean.getCourseName());
			System.out.print(" " + bean.getDescription());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());

		} else {
			System.out.println("Name not found");
		}

	}

	public static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();
		bean.setId(1);
		// bean.setName("m");

		SubjectModel model = new SubjectModel();
		List list = model.search(bean, 1, 1);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.print(" " + bean.getId());
			System.out.print(" " + bean.getName());
			System.out.println(" " + bean.getCourseId());
			System.out.println(" " + bean.getCourseName());
			System.out.print(" " + bean.getDescription());
			System.out.print(" " + bean.getCreatedBy());
			System.out.print(" " + bean.getModifiedBy());
			System.out.print(" " + bean.getCreatedDatetime());
			System.out.print(" " + bean.getModifiedDatetime());

		}
	}
}