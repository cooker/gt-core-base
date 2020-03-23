# 异步操作

GAsyncCall

1. 非事务性

        public void newCall(Call call)
        public <T> void newCall(Call$1<T> call, T arg1
        public <T,A> void newCall(Call$2<T, A> call, T arg1, A arg2)

2. 事务性

- [ ]  待开发