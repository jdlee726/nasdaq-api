package alphatory.share.utils.json;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }
}
