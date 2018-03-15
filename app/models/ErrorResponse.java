package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

/**
 * 服务器出错时的错误消息。
 * <p>
 * code表示出错代码，message是出错摘要，moreInfo包含出错细节URL。
 */
public class ErrorResponse {
  public int code;
  public String message;
  public String developMsg;
  public String moreInfo;

  public JsonNode toJsonNode() {
    return Json.toJson(this);
  }
}