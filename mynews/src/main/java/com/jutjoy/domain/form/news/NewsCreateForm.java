package com.jutjoy.domain.form.news;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewsCreateForm {

    @NotEmpty(message = "＊タイトルは必ず入力してください。")
    @Size(max = 20, message = "＊タイトルは20文字以内で設定してください。")
    private String title;

    @NotEmpty(message = "＊本文は必ず入力してください。")
    private String content;

    private MultipartFile image;
}

// MultipartFile：入力フォームからアップロードされたファイルを扱えるクラス
// 画像データ（本アプリ）やPDF、CSVなどのファイル形式を扱うことができる