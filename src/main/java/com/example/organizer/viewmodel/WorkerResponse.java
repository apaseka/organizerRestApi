package com.example.organizer.viewmodel;

import com.example.organizer.model.Worker;

public class WorkerResponse {

    private Worker worker;
    private String msg;

    public WorkerResponse(Worker worker, String msg) {
        this.worker = worker;
        this.msg = msg;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
