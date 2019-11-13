#importing libraries
from flask import jsonify, request,Flask,json
import requests
from bs4 import BeautifulSoup
BASE_URL="https://www.gogoanime.io/"


def getNew():
    page = requests.get(BASE_URL)
    soup = BeautifulSoup(page.content, 'html5lib') 
    table = soup.find('div', attrs = {'class':'last_episodes loaddub'}).find_all('li')
    data=[ {'url':r.div.a['href'],'title':r.div.a['title'],'img':r.div.img['src'], 'episode':r.find('p','episode').text}     for r in table]
    return json.dumps(data)


def getAnime( url):
    URL=BASE_URL+"category" + url.split("-episode")[0]
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, 'html5lib') 
    ep=soup.find('ul',attrs={'id':'episode_page'})
    url=URL.split('category/')
    url=url[0]+url[1]
    table = soup.find('div', attrs = {'class':'anime_info_body'})
    data={'img':table.img['src'],'title':table.h1.text,'base_url':url,'episodes':ep.a['ep_end'], 'other':[{x.span.text:x.text}  for x in table.find_all('p',attrs={'class':'type'})] }
    return json.dumps(data)


def getEpisode( url,  ep_num):
    url + "-episode-"  + ep_num
    page = requests.get(url)
    soup = BeautifulSoup(page.content, 'html5lib') 
    ep=soup.find('div',attrs={'class':'anime_muti_link'}).find_all('li')
    down=soup.find('div',attrs={'class':'anime_video_body_cate'}).find_all('a')
    data={'stream':[{l['class'][0]:l.a['data-video']} for l in ep ], "download":down[-1]['href']}
    return json.dumps(data)


def search(anime):
    URL=BASE_URL+'search.html?keyword='
    anime= URL+anime
    page = requests.get(anime)
    soup = BeautifulSoup(page.content, 'html5lib') 
    table = soup.find('div', attrs = {'class':'last_episodes'}).find_all('li')
    data=[ {'title':x.find('p','name').a['title'], 'img':x.div.a.img['src'],'url':x.div.a['href'],'released': x.find('p','released').text.strip()}   for x in table]
    return json.dumps(data)


# app
app = Flask(__name__)

@app.route('/', methods=['POST'])
def predict():
    
    # get data
    req = request.get_json(force=True)
    if req["intent"]== 'new':
        return getNew()
    # return data
    elif req["intent"]=="getAnime":
        return getAnime(req["url"])
    
    elif req["intent"]=="episode":
        return getEpisode(req["url"],req["episode_number"])
    elif req['intent']=='search':
        return search(req['anime'])

    return getNew()

if __name__ == '__main__':
    app.run()
