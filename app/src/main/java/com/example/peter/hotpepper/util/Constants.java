package com.example.peter.hotpepper.util;

/**
 * 定数クラス
 */
public class Constants {

    /**
     * コンストラクタ
     */
    private Constants() {
    }

    //URI生成
    static final String HTTP_PROTOCOL = "http";
    static final String AUTHORITY = "webservice.recruit.co.jp";
    static final String PATH_FIRST = "hotpepper/";
    static final String PATH_END = "/v1";
    static final String KEY = "key";
    static final String FORMAT = "format";
    static final String FORMAT_PARAMETER = "json";
    static final String USER_KEY = "8c8598f4d13ca0fa";
    static final String COUNT = "count";
    static final String COUNT_NUMBER = "30";

    // bundleのキー
    public static final String BUNDLE_KEY = "bundle";

    // リクエストパラメータ
    public static final String LARGE_AREA = "large_area";
    public static final String AREA_CODE = "area_code";
    public static final String SHOP_ID = "id";
    public static final String SHOP_CODE = "shop_code";
    public static final String GOURMET = "gourmet";
    public static final String ORDER = "order";
    public static final String ORDER_VALUE = "4";
    public static final String LARGE_AREA_DEFAULT = "Z011";

    // DTO
    public static final String PRIVATE_ROOM = "個室";
    public static final String EXISTENCE = "あり";
    public static final String NO_EXISTENCE = "なし";
    public static final String CARD = "カード";
    public static final String AVAILABLE = "利用可";
    public static final String UNAVAILABLE = "利用不可";
    public static final String NON_SMOKING = "全面禁煙";
    public static final String PARTLY_SMOKING = "一部禁煙";
    public static final String SMOKING = "禁煙席なし";
    public static final String SEPARATE = ",";
    public static final String BLANK = "";
    public static final String GENRE = "ジャンル";
    public static final String ACCESS = "交通アクセス";
    public static final String ADDRESS = "住所";
    public static final String COURSE = "コース";
    public static final String FREE_DRINK = "飲み放題";
    public static final String FREE_FOOD = "食べ放題";
    public static final String HORIGOTATSU = "掘りごたつ";
    public static final String TATAMI = "座敷";
    public static final String AVAILABLE_CARD = "カード可";
    public static final String SMOKING_SEAT = "禁煙席";

    // DB関係
    public static final String SELECTION_ID = "shop_id=?";
    public static final String TABLE_NAME = "bookmark";
    public static final String COLUMN_ID = "shop_id";
    public static final String COLUMN_DATE = "date";
    public static final String[] COLUMNS = {COLUMN_ID, COLUMN_DATE};

    // DB名、バージョン
    public static final String DB_NAME = "bookmark";
    public static final int DB_VERSION = 1;

    // SQL文
    public static final String CREATE_TABLE_SQL = "create table bookmark "
            + "(shop_id text primary key not null,"
            + "date integer not null)";
    public static final String DROP_TABLE_SQL = "drop table if exists bookmark";

    // Activityのタイトル
    public static final String AREA_ACTIVITY = "エリア一覧";
    public static final String SHOP_ACTIVITY = "の店舗一覧";
    public static final String BOOKMARK_ACTIVITY = "ブックマーク一覧";
    public static final String HISTORY_ACTIVITY = "閲覧履歴";

    // Buttonのテキスト
    public static final String REGISTER_BUTTON = "このお店をブックマーク";
    public static final String DELETE_BUTTON = "ブックマークを取り消す";

    // Bookmark時のメッセージ
    public static final String REGISTER_BOOKMARK = "ブックマークに追加しました";
    public static final String DELETE_BOOKMARK = "ブックマークから削除しました";

    //Error Message
    public static final String ERROR_MESSAGE = "null object";

    // TAB名
    private static final String TAB_RECOMMEND = "Recommend";
    private static final String TAB_FAVORITE = "Favorite";
    private static final String TAB_HISTORY = "History";
    public static final String[] TAB_NAME = {TAB_RECOMMEND, TAB_FAVORITE, TAB_HISTORY};
    public static final int RECOMMEND_PAGE = 0;
    public static final int FAVORITE_PAGE = 1;
    public static final int HISTORY_PAGE = 2;

    //Preference
    public static final String PREFERENCE_NAME = "preference_name";
    public static final int PREFERENCE_MAX_SIZE = 20;


}
