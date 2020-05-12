# DialogFragment

#### 基于DialogFragment封装通用的Base类/Base class based on Dialog Fragment



## --------kotlin--------

#### [BaseDialogFragment](https://github.com/Dboy233/DialogFragment/blob/master/app/src/main/java/com/dboy/dialog/kotlin/BaseDialogFragment.kt)

##### Demo

```kotlin
class KotlinDialogFragmentDemo : BaseDialogFragment() {

    override fun getLayoutId(): Int = R.layout.kotlin_dialog_demo_layout

    override fun initViewAndData(savedInstanceState: Bundle?) {
        kotlin_demo_tv.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
    
    override fun getWidthPercentSize(): Float = 1f

    override fun getHeightPercentSize(): Float = 1f
}
```



## --------kotlin  ViewBinding--------

#### [BaseDialogFragment< T : ViewBinding>](https://github.com/Dboy233/DialogFragment/blob/master/app/src/main/java/com/dboy/dialog/kotlin/viewBinding/BaseDialogFragment.kt)

##### Demo

```kotlin
class KotlinViewBindingDialogDemo : BaseDialogFragment<KotlinDialogDemoLayoutBinding>() {

    override fun getRootViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): KotlinDialogDemoLayoutBinding {
        return KotlinDialogDemoLayoutBinding.inflate(inflater, container, false)
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        mRootView.kotlinDemoTv.text =
        "显示 kotlin ViewBinding BaseDialogFragmentDialog Demo"
        
        mRootView.kotlinDemoTv.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun getWidthPercentSize(): Float = 1f

    override fun getHeightPercentSize(): Float = 1f
}
```



## --------Java--------

#### [BaseDialogFragment](https://github.com/Dboy233/DialogFragment/blob/master/app/src/main/java/com/dboy/dialog/java/BaseDialogFragment.java)

##### Demo

```java
public class JavaDialogFragmentDemo extends BaseDialogFragment {
    @Override
    public int getLayoutId() {
        return R.layout.java_dialog_demo_layout;
    }

    @Override
    public void initViewAndData(Bundle savedInstanceState) {
        TextView view = find(R.id.java_demo_tv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public float getWidthPercentSize() {
        return 1;
    }

    @Override
    public float getHeightPercentSize() {
        return 1;
    }
}
```

## --------Java ViewBinding--------

#### [BaseDialogFragment<T extends ViewBinding>](https://github.com/Dboy233/DialogFragment/blob/master/app/src/main/java/com/dboy/dialog/java/viewBinding/BaseDialogFragment.java)

##### Demo

```java
public class JavaViewBindingDialogDemo extends BaseDialogFragment<JavaDialogDemoLayoutBinding> {

    @Override
    public JavaDialogDemoLayoutBinding getRootViewBinding(LayoutInflater inflater, ViewGroup container) {
        return JavaDialogDemoLayoutBinding.inflate(inflater, container, false);
    }

    @Override
    public void initViewAndData(Bundle savedInstanceState) {
        mRootView.javaDemoTv.setText("Java ViewBinding Dialog");
        mRootView.javaDemoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public float getWidthPercentSize() {
        return 1;
    }

    @Override
    public float getHeightPercentSize() {
        return 1;
    }
}

```

