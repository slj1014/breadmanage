package breadmanage;

import java.util.List;

import org.junit.Test;

import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.service.AddRecordServcie;
import cn.edu.uts.web.util.SpringUtil;

public class BeanDaoTest extends SpringUtil{
	AddRecordServcie as=(AddRecordServcie) context.getBean("addRecordServiceImpl");
    @Test
	public void test(){
    List<AddRecord> s=as.findDeadNum();
    System.out.println(s.size());
    }
}
