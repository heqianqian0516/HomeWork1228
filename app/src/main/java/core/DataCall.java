package core;

import bean.Result;

public interface DataCall<T> {

    void success(T data);

    void fail(Result result);

}