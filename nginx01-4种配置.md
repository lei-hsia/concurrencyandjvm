### Nginx 4种配置

`/etc/nginx/nginx.conf`

1. `RR`:
```shell script
user www-data
worker_processes aut0;
pid /run/nginx.pid;

events {
  use epoll;  # since kernel 2.5
  worker_connections 65535;
}

http {

  upstream netease.com {
    server 10.0.0.7:8881;
    server 10.0.0.7:8882;
    server 10.0.0.7:8883;
  }

  server {
    listen 80;
    server_name netease.com;
  
    location / {
      proxy_pass    http://netease.com  # proxy pass
      proxy_set_header  Host        $host;
      proxy_set_header  X-Real-IP   $remote_addr;
    }
  }
}
```
`RR`是默认nginx调度算法，`LC`, `IP_Hash`算法都只是在`upstream`第一行表明:

2. `LC`: 
```shell script
user www-data
worker_processes aut0;
pid /run/nginx.pid;

events {
  use epoll; 
  worker_connections 65535;
}

http {

  upstream netease.com {
    least_conn;
    server 10.0.0.7:8881;
    server 10.0.0.7:8882;
    server 10.0.0.7:8883;
  }

  server {
    listen 80;
    server_name netease.com;
  
    location / {
      proxy_pass    http://netease.com 
      proxy_set_header  Host        $host;
      proxy_set_header  X-Real-IP   $remote_addr;
    }
  }
}
```

3. `IP_Hash`:
```shell script
user www-data
worker_processes aut0;
pid /run/nginx.pid;

events {
  use epoll; 
  worker_connections 65535;
}

http {

  upstream netease.com {
    ip_hash;
    server 10.0.0.7:8881;
    server 10.0.0.7:8882;
    server 10.0.0.7:8883;
  }

  server {
    listen 80;
    server_name netease.com;
  
    location / {
      proxy_pass    http://netease.com 
      proxy_set_header  Host        $host;
      proxy_set_header  X-Real-IP   $remote_addr;
    }
  }
}
```

`weight`没有表明算法名称，但是也是 `upstream` 部分写明weight:

4. `weight`:
```shell script
user www-data
worker_processes aut0;
pid /run/nginx.pid;

events {
  use epoll; 
  worker_connections 65535;
}

http {

  upstream netease.com {
    server 10.0.0.7:8881 weight=3;
    server 10.0.0.7:8882 weight=2;
    server 10.0.0.7:8883 weight=1;
  }

  server {
    listen 80;
    server_name netease.com;
  
    location / {
      proxy_pass    http://netease.com 
      proxy_set_header  Host        $host;
      proxy_set_header  X-Real-IP   $remote_addr;
    }
  }
}
```