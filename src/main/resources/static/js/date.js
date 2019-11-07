var DateUtil = {};
/*******************************************************************************
 * 格式化时间:如yyyy-MM-dd HH:mm:SS
 */
DateUtil.format = function(dateFormat, strDate) {
	var d = new Date();
	if(strDate){
		d = new Date(strDate);
	}
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var month2 = month;
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	var day = d.getDate();
	var day2 = day;
	if (day >= 0 && day <= 9) {
		day = "0" + day;
	}
	var hour = d.getHours();
	var hour2 = hour;
	if (hour >= 1 && hour <= 9) {
		hour = "0" + hour;
	}
	var minute = d.getMinutes();
	var minute2 = minute;
	if (minute >= 1 && minute <= 9) {
		minute = "0" + minute;
	}

	var second = d.getSeconds();
	var second2 = second;
	if (second >= 1 && second <= 9) {
		second = "0" + second;
	}
	var currentdate = dateFormat.replace("yyyy", year).replace("MM", month)
			.replace("M", month2).replace("dd", day).replace("d", day2)
			.replace("HH", hour).replace("H", hour2).replace("mm", minute)
			.replace("m", minute2).replace("SS", second).replace("SS", second2);
	return currentdate;
};

DateUtil.getDateRange = function(type){
	//type:1今天、2昨天、3近一周、4近一月、5本周、6本月
	var today = new Date();
	var strDate;
	var dates = {};
	switch (type) {
	case 1: //今天
		strDate = DateUtil.format("yyyy-MM-dd", today);
		dates.startDate = strDate;
		dates.endDate = strDate;
		break;
	case 2: //昨天
		var yesterday = new Date(today - 24 * 60 * 60 * 1000);
		strDate = DateUtil.format("yyyy-MM-dd", yesterday);
		dates.startDate = strDate;
		dates.endDate = strDate;
		break;
	case 3: //近一周
		var oneWeekAgo = new Date(today - 6 * 24 * 60 * 60 * 1000);
		strDate = DateUtil.format("yyyy-MM-dd", oneWeekAgo);
		dates.startDate = strDate;
		
		strDate = DateUtil.format("yyyy-MM-dd", today);
		dates.endDate = strDate;
		break;
	case 4: //近一月
		var y = today.getFullYear();
		var m = today.getMonth();
		var d = today.getDate();
		var oneMonthAgo = new Date(y, m - 1, d + 1);
		strDate = DateUtil.format("yyyy-MM-dd", oneMonthAgo);
		dates.startDate = strDate;
		
		strDate = DateUtil.format("yyyy-MM-dd", today);
		dates.endDate = strDate;
		break;
	case 5: //本周,星期一当作星期第一天，星期日为最后天
		var y = today.getFullYear();
		var m = today.getMonth();
		var d = today.getDate();
		var dayOfWeek = today.getDay();
		if (dayOfWeek == 0)
			dayOfWeek = 7;
		var firstDay = new Date(y, m, d - dayOfWeek + 1);
		strDate = DateUtil.format("yyyy-MM-dd", firstDay);
		dates.startDate = strDate;
		
		strDate = DateUtil.format("yyyy-MM-dd", today);
		dates.endDate = strDate;
		break;
	case 6: //本月
		var y = today.getFullYear();
		var m = today.getMonth();
		var firstDay = new Date(y, m, 1);
		strDate = DateUtil.format("yyyy-MM-dd", firstDay);
		dates.startDate = strDate;
		
		strDate = DateUtil.format("yyyy-MM-dd", today);
		dates.endDate = strDate;
		break;

	default:
		break;
	}
	return dates;	
};
