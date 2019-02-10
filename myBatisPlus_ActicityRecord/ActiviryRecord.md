###  ActivityRecord 
 java bean 继承 Model 类（com.baomidou.mybatisplus.activerecord.Model）
 
 - 重写下面的方法
```java
        /**
	 * 主键值
	 */
	protected Serializable pkVal() {
		return id;
	}
```


 