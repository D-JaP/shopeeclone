// change link 's hostname change to your hostname
export const changeLinkHostname = (to_do_url: string) => {
    let url = new URL(to_do_url);
    
    url.hostname = window.location.hostname;
    url.port = window.location.port;

    return url.toString();

}