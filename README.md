# ArchitechCalculator
# description (bad English :( )
a simple and lightweight util for calculation (or simple check...)
      
why i make it?
- because JavaScript do calculation work is so slow
- so i make it.
      
### 简述 (简体中文)
一个简单而轻量级的计算工具
      
为什么我会去制作它?
- 因为JavaScript用于计算的时候太慢了
- 所以我制作了它
      
### EXAMPLE 示例
- **CALCULATOR**
- **计算**
```
- Calculator.eval("1 + 5 * 30 + (10 * 10)");
- return 251.0
```
      
- **SIMPLE CHECK**
- **简单判断**
```
- Calculator.eval("1 + 5 > 5");
- return 1.0 (true)
```
   
- **ATTENTION**
- **注意事项**
>you should register calculate symbols before do calculator work
-   在使用计算功能之前,你应该要先去将计算符号注册<br/>

>      for example 
>      例如
```
-  new SymbolReduce().register();
```
   
-  please implements interface CalculatorSymbol and register it if you want to make a custom symbol.
-  如果你要自定义计算符号,请继承接口CalculatorSymbol并在使用之前将它注册<br/>
      
My English so bad.<br/>
I am so sorry if you cant understand<br/>
