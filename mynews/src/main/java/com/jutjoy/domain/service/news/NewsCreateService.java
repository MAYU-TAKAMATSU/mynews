package com.jutjoy.domain.service.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.common.CommonConstant;
import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsCreateService {

    @Autowired
    private NewsRepository newsRepository;

	public void create(NewsCreateForm form) { //createメソッド

        MultipartFile image = form.getImage();

        // ニュース登録
        News news = createNews(form); //インスタンス化、createNewsメソッドの呼び出し

        // 画像保存
        if (!image.getOriginalFilename().isEmpty()) {

            // フォルダ作成
			String dirPath = CommonConstant.FILE_PATH + File.separator + news.getId(); /*変数dirPathに格納するフォルダパスを記述。File.separator→実行OSに対応したセパレータを返す*/
			File uploadDir = new File(dirPath); /*Fileクラス：ファイルやフォルダを操作する、インスタンスの引数に操作するファイル(フォルダ)名を指定*/
			if (!uploadDir.exists()) { /*existsメソッドでフォルダの有無を確認→無い場合makedirメソッドで作成*/
                uploadDir.mkdirs();
            }

            try {
                // ファイル作成
				String fullPath = uploadDir.getPath() + File.separator + image.getOriginalFilename(); 
                File imageFullPath = new File(fullPath);
                try (FileOutputStream fileOutputStream = new FileOutputStream(imageFullPath); /*□□Streamクラス：ファイル操作で使用*/
						BufferedOutputStream uploadFileStream = new BufferedOutputStream(fileOutputStream)) { /*BuffereOutputStreamクラス：データ全般を書き込むためのクラス*/

                    byte[] bytes = image.getBytes();
					uploadFileStream.write(bytes); /*writeメソッドでファイル出力*/
                }

            } catch (IOException e) {
                e.printStackTrace(); //Throwableクラスのメソッド、例外発生時経たメソッドがわかる
            }
        }
    }

	private News createNews(NewsCreateForm form) { /*createNewsメソッド*/

		News entity = new News(); /*インスタンス化、Newsクラス(エンティティクラス)を変数名entityで */
        entity.setTitle(form.getTitle());
        entity.setContent(form.getContent());
		entity.setImageName(Objects.isNull(form.getImage()) ? null : form.getImage().getOriginalFilename()); /*画像が無い場合ヌルポ(任意項目)*/

		return newsRepository.save(entity); /*saveメソッド：登録したエンティティを返す*/
    }
}