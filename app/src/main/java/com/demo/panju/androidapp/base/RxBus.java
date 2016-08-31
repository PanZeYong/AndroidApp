package com.demo.panju.androidapp.base;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class RxBus {

    private static RxBus sInstance = null;

    private Subject<Object, Object>  _bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance() {
        if (null == sInstance) {
            synchronized (RxBus.class) {
                if (null == sInstance) {
                    sInstance = new RxBus();
                }
            }
        }

        return sInstance;
    }

    public void post(Object o) {
        if (hasObserver()) {
            _bus.onNext(o);
        }
    }

    public Observable<Object> toObservable() {
        return _bus;
    }

    private boolean hasObserver() {
        return _bus.hasObservers();
    }
}
