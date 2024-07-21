import { useState, useCallback } from "react";

const useFetch = (url) => {
  const [data, setData] = useState([]);

  const fetchData = useCallback(async () => {
    const jwtToken = localStorage.getItem("jwt");
    const response = await fetch(url, {
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });
    const result = await response.json();
    setData(result);
  }, [url]);

  return [data, fetchData];
};

export default useFetch;