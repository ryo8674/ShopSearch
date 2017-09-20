# ShopSearch

## 機能
* エリア選択
* 店舗選択
* 店舗詳細
* ブックマーク機能

## 仕様
* NavigationDrawerを使用する
* Bookmarkは登録順

## 拡張
* Bookmark画面に閲覧履歴を表示
  * TabLayout + ViewPagerで切り替えができる
  * Dao -> findByDate 最新20件ぐらい -> ブックマークも最新20件のみ表示に
  
### TODO
>  * Fragmentの処理一部変更
>  * FragmentPagerAdapterの作成
>  * ブックマーク画面のレイアウト変更
