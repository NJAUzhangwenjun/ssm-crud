package cn.wen.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张文军
 * @Description:提示信息类
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/2/2517:21
 */
public class Msg {
		/**
		 * 提示信息，100：成功，200：失败
		 */
		private int code;
		/**
		 * 提示信息
		 */
		private String msg;
		/**
		 * 用户要返回给浏览器的数据
		 */
		private Map<String, Object> extend = new HashMap<String, Object>();

		/**
		 * 成功
		 * @return
		 */
		public static Msg success() {
				Msg result = new Msg();
				result.setCode(100);
				result.setMsg("成功！");
				return result;
		}

		/**
		 * 失败
		 * @return
		 */
		public static Msg fail() {
				Msg result = new Msg();
				result.setCode(200);
				result.setMsg("失败！");
				return result;
		}

		/**
		 * 快速添加的方法
		 * @param key
		 * @param value
		 * @return
		 */
		public  Msg add(String key,Object value) {
				this.getExtend().put(key, value);
				return this;
		}

		public int getCode() {
				return code;
		}

		public void setCode(int code) {
				this.code = code;
		}

		public String getMsg() {
				return msg;
		}

		public void setMsg(String msg) {
				this.msg = msg;
		}

		public Map<String, Object> getExtend() {
				return extend;
		}

		public void setExtend(Map<String, Object> extend) {
				this.extend = extend;
		}
}
