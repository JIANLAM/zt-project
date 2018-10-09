package com.szcti.lcloud.exchange.idc.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import com.szcti.lcloud.exchange.idc.vo.R;

public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1674168618746323999L;

	public MyRuntimeException(String message) {
		super(message);
	}

	private R r;

	public MyRuntimeException(R r) {
		super(r.getMessage());
		this.setR(r);
	}

	public R getR() {
		return r;
	}

	public void setR(R r) {
		this.r = r;
	}

	public static void showTraces(Throwable t) {
		Throwable next = t.getCause();
		if (next == null) {
			if (t instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException mce = (SQLIntegrityConstraintViolationException) t;
				throw new MyRuntimeException(
						new R("APP0001", "参数错误：" + mce.getMessage().substring(0, mce.getMessage().indexOf("for"))));
			}
			if (t instanceof ConstraintViolationException) {
				ConstraintViolationException cve = (ConstraintViolationException) t;
				List<String> errorParams = new ArrayList<>();
				// 获得验证失败的类 constraintViolation.getLeafBean()
				// 获得验证失败的值 constraintViolation.getInvalidValue()
				// 获取参数值 constraintViolation.getExecutableParameters()
				// 获得返回值 constraintViolation.getExecutableReturnValue()
				cve.getConstraintViolations().forEach(cv -> errorParams.add(cv.getPropertyPath().toString() + cv.getMessage()) );
				throw new MyRuntimeException(new R("APP0001", "参数错误：" + errorParams));
			}
		} else {
			showTraces(next);
		}
	}
}