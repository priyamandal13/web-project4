package in.com.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.com.rays.bean.CourseBean;
import in.com.rays.model.CourseModel;

public class TestCourse {

	public static void main(String[] args) throws Exception {

		 testAdd();
		 // testUpdate();
	    // testDelete();
		// testSearch()
		

	}

	public static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();
		bean.setId(15);
		bean.setName("priya");
		bean.setDuration("");
		bean.setDescription("mandal");
		bean.setCreatedBy("rajl@gmail.com");
		bean.setModifiedBy("aryan@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

	    CourseModel model = new CourseModel();
		model.add(bean);
		


	}

	public static void testUpdate() throws Exception {

		CourseBean bean = new CourseBean();
		bean.setId(1);
		bean.setName("suraj");
		bean.setDuration("");
		bean.setDescription("yadav");
		bean.setCreatedBy("Ssuraj@gmail.com");
		bean.setModifiedBy("suraj@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));


		CourseModel model = new CourseModel();
		model.update(bean);

	}

	public static void testDelete() throws Exception {

	CourseModel model = new CourseModel();
		model.delete(1);
	}

		public static void testFindByPk() throws Exception {
			CourseModel model = new CourseModel();
			CourseBean bean = model.findByPk(1);
			if (bean != null) {
				System.out.print(" " + bean.getId());
				System.out.print(" " + bean.getName());
				System.out.print(" " + bean.getDuration());
				System.out.print(" " + bean.getDescription());
				System.out.print(" " + bean.getCreatedBy());
				System.out.print(" " + bean.getModifiedBy());
				System.out.print(" " + bean.getCreatedDatetime());
				System.out.print(" " + bean.getModifiedDatetime());
			} else {
				System.out.println(" id not found ");
			}
			

		}

		public static void testSearch() throws Exception {

		CourseBean bean = new CourseBean();
			bean.setId(1);
			// bean.setName("m");

			CourseModel model = new CourseModel();
			List list = model.search(bean, 1, 1);
			Iterator i = list.iterator();
			while (i.hasNext()) {
				bean = (CourseBean) i.next();
				System.out.print(" " + bean.getId());
				System.out.print(" " + bean.getName());
				System.out.println(" " + bean.getDuration() );
				System.out.print(" " + bean.getDescription());
				System.out.print(" " + bean.getCreatedBy());
				System.out.print(" " + bean.getModifiedBy());
				System.out.print(" " + bean.getCreatedDatetime());
				System.out.print(" " + bean.getModifiedDatetime());

			}
		}
	}