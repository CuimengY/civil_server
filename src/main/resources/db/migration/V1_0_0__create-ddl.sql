--序列
CREATE SEQUENCE public.daily_plan_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.master_plan_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

-- 国考岗位信息
CREATE TABLE public.nationaljob (
                                    departmentid int8 NOT NULL,
                                    departmentname varchar(2048) NULL,
                                    bureau varchar(2048) NULL,
                                    bureaunature varchar(2048) NULL,
                                    "name" varchar(2048) NULL,
                                    nature varchar(2048) NULL,
                                    distribution varchar(2048) NULL,
                                    introduction varchar(2048) NULL,
                                    id int8 NOT NULL,
                                    "level" varchar(2048) NULL,
                                    examcatagory varchar(2048) NULL,
                                    count int8 NULL,
                                    major varchar(2048) NULL,
                                    qualification varchar(2048) NULL,
                                    "degree" varchar(2048) NULL,
                                    political varchar(2048) NULL,
                                    "grassroots " varchar(2048) NULL,
                                    experience varchar(2048) NULL,
                                    professionaltest varchar(2048) NULL,
                                    proportion varchar(2048) NULL,
                                    workposition varchar(2048) NULL,
                                    settleposition varchar(2048) NULL,
                                    remark varchar(2048) NULL,
                                    website varchar(2048) NULL,
                                    number1 varchar(2048) NULL,
                                    number2 varchar(2048) NULL,
                                    number3 varchar(2048) NULL,
                                    followed bool NULL DEFAULT false,
                                    CONSTRAINT nationaljob_pk PRIMARY KEY (departmentid, id)
);

-- 省考岗位
CREATE TABLE public.provincialjob (
                                      id int8 NOT NULL,
                                      unit varchar(2048) NULL,
                                      sum int4 NULL,
                                      department varchar(2048) NULL,
                                      "name" varchar(2048) NULL,
                                      introduction varchar(2048) NULL,
                                      count int4 NULL,
                                      major varchar(2048) NULL,
                                      qualification varchar(2048) NULL,
                                      otherterms varchar(2048) NULL,
                                      catagory varchar(2048) NULL,
                                      "level" varchar(2048) NULL,
                                      subjecttype varchar(2048) NULL,
                                      nature varchar(2048) NULL,
                                      "number" varchar(2048) NULL,
                                      psychologicaltest varchar(2048) NULL,
                                      physicalfitness varchar(2048) NULL,
                                      followed bool NULL DEFAULT false,
                                      CONSTRAINT provincialjob_pk PRIMARY KEY (id)
);

-- 总计划
CREATE TABLE public.masterplan (
                                   id int4 NOT NULL DEFAULT nextval('master_plan_id_seq'::regclass),
                                   plan varchar NULL,
                                   part varchar NULL,
                                   complete bool NULL DEFAULT false,
                                   CONSTRAINT masterplan_pk PRIMARY KEY (id)
);

-- 日计划
CREATE TABLE public.dailyplan (
                                  plan varchar NULL,
                                  "date" varchar NOT NULL,
                                  id int4 NULL DEFAULT nextval('daily_plan_id_seq'::regclass),
                                  complete bool NULL DEFAULT false
);