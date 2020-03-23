# 限流

# 样例

- 注意事项
    - 继承 BaseController
    - 添加注解：@GLimit

    @Slf4j
    @RestController
    @RequestMapping("/")
    public class HelloAction extends BaseController {
    
        @RequestMapping("/")
        @GLimit(value = "111", req = 2)
        public String hello(){
            return "hello";
        }
    }

# 注解

@GLimit

value 限流名称

req 每秒请求量

cache 默认实现

# 异常

LimitOverException