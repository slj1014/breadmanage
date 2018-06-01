package breadmanage;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.service.AddRecordServcie;
import cn.edu.uts.web.util.MD5Util;

public class MD5Test {
	@Test
	public void test() {
		System.out.println(MD5Util.GetMD5Code("admin"));
	}
}
