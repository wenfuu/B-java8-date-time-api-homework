package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingSystemV3 {

  public static void main(String[] args) {
    String timeStr = "2020-04-01 14:30:00";

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 从字符串解析得到会议时间
    LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
    ZonedDateTime zonedMeetingTimeInLondon = meetingTime.atZone(ZoneId.of("Europe/London"));

    ZonedDateTime zonedMeetingTimeInBeijing = zonedMeetingTimeInLondon.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
    LocalDateTime meetingTimeInBeijing = zonedMeetingTimeInBeijing.toLocalDateTime();

    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(meetingTimeInBeijing)) {
      LocalDateTime tomorrow = now.plusDays(1);
      int newDayOfYear = tomorrow.getDayOfYear();
      meetingTimeInBeijing = meetingTimeInBeijing.withDayOfYear(newDayOfYear);
      zonedMeetingTimeInBeijing = meetingTimeInBeijing.atZone(ZoneId.of("Asia/Shanghai"));
      ZonedDateTime zonedMeetingTimeInChicago = zonedMeetingTimeInBeijing.withZoneSameInstant(ZoneId.of("America/Chicago"));
      // 格式化新会议时间
      String showTimeStr = formatter.format(zonedMeetingTimeInChicago.toLocalDateTime());
      System.out.println(showTimeStr);
    } else {
      System.out.println("not start yet");
    }
  }
}
