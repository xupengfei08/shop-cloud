FROM java:8

LABEL maintainer="xpf@facemeng.com.cn"

ADD user-core.jar /usr/local/src/

WORKDIR /usr/local/src

EXPOSE 9020

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+08","user-core.jar"]

##############################################################

yum -y install docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://jocnrpke.mirror.aliyuncs.com"],
  "max-concurrent-downloads": 10
}
EOF
sudo systemctl daemon-reload
sudo systemctl start docker
sudo systemctl enable docker

yum install -y kubelet-1.13.1 kubeadm-1.13.1 kubectl-1.13.1
systemctl enable kubelet && systemctl start kubelet

##### Master #####

kubeadm init --kubernetes-version=v1.13.1 --apiserver-advertise-address 192.168.50.100 --pod-network-cidr=10.244.0.0/16

kubeadm join 192.168.50.100:6443 --token ow28gd.78i6iyia4ab84cue --discovery-token-ca-cert-hash sha256:7cc0f70c7759c71d41bb4ff346ee186a1a91106a9d9b767e244d2e9bebdf3f35

##### Master #####
openssl x509 -req -sha256 -days 3650 -in dashboard.csr -signkey dashboard.key -out dashboard.crt

token:      eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi10b2tlbi1jNW01bCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImM1ZThiOGQ0LTM1MjQtMTFlOS04MGJlLTAwNTA1NjNkM2M2YSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTphZG1pbiJ9.Q2GT_YeDBTLnDA31gxwviL7ZbZ9sYLowIUzeCLK9MwLVnm1AYb8jHJ7vN3TTDm6qtY9khPIgwStECuEdUlKFFeYwm_61fSxrqC-TE1CiNdgNMObzkayVRhDUSe0sSoT2Jd3qnM-xcMJnMEirm8PxtqOAJ7hwiQC6k4RBPYlkq1--BRK2k9B006AcX6ckRjGfmibIRyt3gwzK9oEYaDkHhUiezzN6qNzSjiFauKX1jz30SJkP2eJWP5iyavX3sWRA1c0B-NWBjNnuU7d_LNXkXg1Xrico8I9imeb28EmzXMzril77qkGfoIWCjSjpElcz_QwIsQv4fJ11hCugpXiDHA


docker run --name nginx -p 8080:80 -p 8090:8090 -v /data/nginx/config/nginx.conf:/etc/nginx/nginx.conf -v /data/nginx/html:/etc/nginx/html -v /data/nginx/logs:/var/log/nginx -v /etc/localtime:/etc/localtime:ro --restart always -d nginx

docker run --name szsx -p 9016:9016 -v /Users/suanmilk/nginx.conf:/etc/nginx/nginx.conf -v /Users/suanmilk/school-project:/root/www/school-project --restart always -d nginx
docker run --name szsx-face -p 9016:9016 -v /usr/local/src/szsx/nginx.conf:/etc/nginx/nginx.conf -v /usr/local/src/szsx/dist:/root/www/school-project --restart always -d nginx


