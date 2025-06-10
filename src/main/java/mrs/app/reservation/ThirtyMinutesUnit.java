package mrs.app.reservation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented// このアノテーション自体が Javadoc にも表示されるようになる,使用例などを API ドキュメントに表示
@Constraint(validatedBy = {ThirtyMinutesUnitValidator.class})// このアノテーションが バリデーション制約（Constraint）であるvalidatedBy で指定されたクラス（ThirtyMinutesUnitValidator）が、実際のバリデーションロジックを担当
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })// このアノテーションをどこで使えるかを指定。メソッドやフィールドなどに付けられる
@Retention(RUNTIME)// 実行時（RUNTIME）までアノテーション情報を保持する
public @interface ThirtyMinutesUnit {
	/* 
	 * バリデーションエラーが発生したときに表示されるエラーメッセージのデフォルト値。
	 * "{}" はバリデーション用のメッセージリソース（.properties）から取得されるプレースホルダーです
	 */
	String message() default "{mrs.app.reservation.ThirtyMinutesUnit.message}";
	
	/*
	 * バリデーショングループを指定するためのプロパティ。
	 * 複数段階のバリデーションや条件付きバリデーションで使用します。
	 * <?> は **ワイルドカード（任意の型）**で、配列内のクラス型を表します。
	 */
	Class<?>[] groups() default {};
	
	/*
	 * バリデーションの「重み付け」や「種類付け」に使われる情報を指定できます。
	 * 通常のバリデーションではあまり使わず、セキュリティやログなど特殊なケースで使用されます。
	 */
	Class<? extends Payload>[] payload() default {};
	
	
	/*
	 * 
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	// @interface は アノテーションの定義を表します。ここでは ThirtyMinutesUnit という **独自アノテーション（カスタムバリデーション）**を作成しています。
	public @interface List {
		// 複数の ThirtyMinutesUnit を格納するための value プロパティです。
		ThirtyMinutesUnit[] value();
	}
	
}
