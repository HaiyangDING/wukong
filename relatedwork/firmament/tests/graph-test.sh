kubectl create -f graph-job2.yaml &
sleep 5
kubectl create -f graph-job1.yaml &
