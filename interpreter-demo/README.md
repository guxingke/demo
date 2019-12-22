# 解释器 DEMO

# 环境要求
- maven 3.3+
- jdk 1.8+

# 构建
```bash
mvn clean package

```

# 使用

## 寄存器

```bash
$ java -jar target/interpreter-demo.jar register
> 2

# or
$ java -cp target/interpreter-demo.jar com.gxk.demo.register.RegisterDemo
> 2
```

## 栈
```bash
$ java -jar target/interpreter-demo.jar stack
> 2

# or
$ java -cp target/interpreter-demo.jar com.gxk.demo.stack.StackDemo
> 2
```

## 混合
```bash
$ java -jar target/interpreter-demo.jar hybrid
> 2

# or
$ java -cp target/interpreter-demo.jar com.gxk.demo.hybrid.HybridDemo
> 2
```


