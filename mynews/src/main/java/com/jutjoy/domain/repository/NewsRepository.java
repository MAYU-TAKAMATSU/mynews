package com.jutjoy.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.news.News;

@Repository /* リポジトリであることを示すアノテーション */
public interface NewsRepository extends JpaRepository<News, Integer> { 
	/* インターフェースの継承元 
	 * Newsクラス(エンティティクラス)、＠Idが指定されているプロパティのクラス(idなのでInteger)*/

public List<News> findAllByOrderById();
	//findAll：全権取得　OrderById：idでソート(昇順)
	//戻り値List<News>：複数件取得の可能性があるためListで返す
public List<News> findByTitleLike(String title);
	//引数に指定した文字列でLike検索、メソッド名の後ろにLikeを付与するとLike検索を実装することができる
	//※SQLで記述すると、SELECT * FROM news WHERE title LIKE 検索値;
}